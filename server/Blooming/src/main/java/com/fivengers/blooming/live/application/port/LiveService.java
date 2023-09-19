package com.fivengers.blooming.live.application.port;

import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService implements LiveSearchUseCase {

    private final LivePort livePort;

    @Override
    public List<Live> searchByKeyword(String query, Pageable pageable) {
        return livePort.findByKeyword(query, pageable);
    }

    @Override
    public List<Live> searchByArtist(String query, Pageable pageable) {
        return livePort.findByArtistStageName(query, pageable);
    }

}
