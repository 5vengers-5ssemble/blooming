package com.fivengers.blooming.membership.application.port;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.membership.MembershipApplicationNotFoundException;
import com.fivengers.blooming.membership.application.port.in.MembershipApplicationUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplicationModifyRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplyRequest;
import com.fivengers.blooming.membership.application.port.out.ContractPort;
import com.fivengers.blooming.membership.application.port.out.MembershipApplicationPort;
import com.fivengers.blooming.membership.application.port.out.MembershipPort;
import com.fivengers.blooming.membership.application.port.out.dto.ContractDeployRequest;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.fivengers.blooming.membership.domain.NftSale;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipApplicationService implements MembershipApplicationUseCase {

    private final MembershipApplicationPort membershipApplicationPort;
    private final ArtistPort artistPort;
    private final MembershipPort membershipPort;
    private final ContractPort contractPort;

    @Override
    public MembershipApplication add(MembershipApplyRequest request, Long memberId) {
        return membershipApplicationPort.save(request.toDomain(artistPort
                .findByMemberId(memberId)
                .orElseThrow(ArtistNotFoundException::new)));
    }

    @Override
    public MembershipApplication searchByMemberIdAndApplicationState(Long memberId,
            MembershipApplicationState applicationState) {
        return membershipApplicationPort
                .findByMemberIdAndApplicationState(memberId, applicationState)
                .orElseThrow(MembershipApplicationNotFoundException::new);
    }

    @Override
    public Page<MembershipApplication> searchAll(Pageable pageable,
            MembershipApplicationState applicationState) {
        return membershipApplicationPort.findByApplicationState(pageable, applicationState);
    }

    @Override
    public MembershipApplication modifyStateById(MembershipApplicationModifyRequest request,
            Long applicationId) throws Exception {
        MembershipApplication application = membershipApplicationPort.findById(
                        applicationId)
                .orElseThrow(MembershipApplicationNotFoundException::new);
        application.changeState(request.applicationState());

        MembershipApplication updatedApplication = membershipApplicationPort.update(application);
        saveIfApproved(updatedApplication);
        return updatedApplication;
    }

    private void saveIfApproved(MembershipApplication application) throws Exception {
        if (application.getApplicationState().equals(MembershipApplicationState.APPROVAL)) {

            membershipPort.save(Membership.builder()
                    .title(application.getTitle())
                    .symbol(application.getTitle().toUpperCase())
                    .description(application.getDescription())
                    .season(1)
                    .seasonStart(application.getSeasonStart())
                    .seasonEnd(application.getSeasonEnd())
                    .purchaseStart(application.getPurchaseStart())
                    .purchaseEnd(application.getPurchaseEnd())
                    .saleCount(application.getSaleCount())
                    .salePrice(application.getSalePrice())
                    .thumbnailUrl(application.getThumbnailUrl())
                    .baseUri(application.getBaseUri())
                    .contractAddress(checkPrivateKey(application))
                    .artist(application.getArtist())
                    .nftSale(NftSale.builder()
                            .totalNftCount(application.getSaleCount())
                            .soldNftCount(0)
                            .totalNftAmount(
                                    application.getSaleCount() * application.getSalePrice())
                            .soldNftAmount(0L)
                            .build())
                    .build());
        }
    }

    private String checkPrivateKey(MembershipApplication application) throws Exception {

        if (application.getPrivateKey().equals("test")) {
            return "test";
        }
        return contractPort.deploy(ContractDeployRequest.from(application));
    }
}
