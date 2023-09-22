use blooming;

INSERT INTO member (oauth_account, oauth_provider, name, nickname, account, deleted)
VALUES ('12345', 'GOOGLE', 'Example Name', 'Example Nickname', 'example_account', false);

INSERT INTO artist (stage_name, agency, description, profile_image_url, youtube_url, fan_cafe_url, sns_url, deleted, member_id)
VALUES ('Example Stage Name', 'Example Agency', 'Example Artist Description',
        'https://newsimg.sedaily.com/2021/12/09/22V85NTJGY_1.jpg', 'https://www.youtube.com/@On8ight', 'https://www.youtube.com/@On8ight', 'https://www.youtube.com/@On8ight',
        0, 1);

INSERT INTO project(name, funding_amount, target_amount, started_at, ended_at, introduction, description, teaser_video_url, revenue_percent, profile_img, deleted, artist_id, created_at, modified_at)
VALUES
    ('프로젝트 1', 5000000, 10000000, '2022-09-21', '2022-10-21', '프로젝트 1 소개', '프로젝트 1 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 10, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2022-09-21', '2022-09-21'),
    ('프로젝트 2', 3000000, 7500000, '2022-09-22', '2022-10-22', '프로젝트 2 소개', '프로젝트 2 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 15, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2022-09-22', '2022-09-22'),
    ('프로젝트 3', 8000000, 12000000, '2022-09-23', '2022-10-23', '프로젝트 3 소개', '프로젝트 3 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 12, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2022-09-23', '2022-09-23'),
    ('프로젝트 4', 6000000, 9000000, '2022-09-24', '2022-10-24', '프로젝트 4 소개', '프로젝트 4 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 18, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2022-09-24', '2022-09-24'),
    ('프로젝트 5', 7000000, 11000000, '2022-09-25', '2022-10-25', '프로젝트 5 소개', '프로젝트 5 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 20, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2022-09-25', '2022-09-25'),
    ('프로젝트 6', 4000000, 8000000, '2022-09-26', '2022-10-26', '프로젝트 6 소개', '프로젝트 6 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 0, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2022-09-26', '2022-09-26'),
    ('프로젝트 7', 9000000, 15000000, '2023-09-27', '2023-10-27', '프로젝트 7 소개', '프로젝트 7 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 0, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2023-09-27', '2023-09-27'),
    ('프로젝트 8', 5500000, 10500000, '2023-09-28', '2023-10-28', '프로젝트 8 소개', '프로젝트 8 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 0, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2023-09-28', '2023-09-28'),
    ('프로젝트 9', 7500000, 12500000, '2023-09-29', '2023-10-29', '프로젝트 9 소개', '프로젝트 9 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 0, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2023-09-29', '2023-09-29'),
    ('프로젝트 10', 6500000, 9500000, '2023-09-30', '2023-10-30', '프로젝트 10 소개', '프로젝트 10 설명', 'https://www.youtube.com/watch?v=oKKZQOVI8ok&t=2156s', 0, 'https://health.chosun.com/site/data/img_dir/2023/06/08/2023060801867_0.jpg', 0, 1, '2023-09-30', '2023-09-30');

INSERT INTO concert(project_id, poster_img_url,setlist_img_url,goods_img_url)
VALUES
    (1, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (2, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (3, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (4, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (5, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (6, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (7, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (8, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (9, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (10, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg');

INSERT INTO activity(project_id, album_img_url, tracklist_img_url, composition_img_url)
VALUES
    (1, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (2, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (3, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (4, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (5, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (6, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (7, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (8, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (9, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg'),
    (10, 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg');


INSERT INTO investment_overview(project_id, publisher, type, redemption_type, financing_purpose, price_per_account, minimum_price, minimum_funding_amount, maximum_funding_amount, funding_started_at, funding_ended_at, investment_published_at, investment_matured_at, created_at, modified_at)
VALUES
    (1, '출판사1', '유형1', '상환유형1', '자금용도1', 100000, 100000, 2000000, 10000000, '2023-09-21', '2023-09-30', '2023-09-25', '2023-10-30', '2023-09-21', '2023-09-21'),
    (2, '출판사2', '유형2', '상환유형2', '자금용도2', 120000, 120000, 2200000, 11000000, '2023-09-22', '2023-09-29', '2023-09-26', '2023-10-29', '2023-09-22', '2023-09-22'),
    (3, '출판사3', '유형3', '상환유형3', '자금용도3', 150000, 150000, 2500000, 12000000, '2023-09-23', '2023-09-28', '2023-09-27', '2023-10-28', '2023-09-23', '2023-09-23'),
    (4, '출판사4', '유형4', '상환유형4', '자금용도4', 130000, 130000, 2300000, 11500000, '2023-09-24', '2023-09-27', '2023-09-28', '2023-10-27', '2023-09-24', '2023-09-24'),
    (5, '출판사5', '유형5', '상환유형5', '자금용도5', 180000, 180000, 2800000, 14000000, '2023-09-25', '2023-09-26', '2023-09-29', '2023-10-26', '2023-09-25', '2023-09-25'),
    (6, '출판사6', '유형6', '상환유형6', '자금용도6', 110000, 110000, 2100000, 10500000, '2023-09-26', '2023-09-25', '2023-09-30', '2023-10-25', '2023-09-26', '2023-09-26'),
    (7, '출판사7', '유형7', '상환유형7', '자금용도7', 160000, 160000, 2600000, 13000000, '2023-09-27', '2023-09-24', '2023-09-24', '2023-10-24', '2023-09-27', '2023-09-27'),
    (8, '출판사8', '유형8', '상환유형8', '자금용도8', 140000, 140000, 2400000, 12000000, '2023-09-28', '2023-09-23', '2023-09-23', '2023-10-23', '2023-09-28', '2023-09-28'),
    (9, '출판사9', '유형9', '상환유형9', '자금용도9', 170000, 170000, 2700000, 13500000, '2023-09-29', '2023-09-22', '2023-09-22', '2023-10-22', '2023-09-29', '2023-09-29'),
    (10, '출판사10', '유형10', '상환유형10', '자금용도10', 1200, 500, 2200000, 11000000, '2023-09-30', '2023-09-21', '2023-09-21', '2023-10-21', '2023-09-30', '2023-09-30');

INSERT INTO view_count(created_at, modified_at, project_id, view_count)
VALUES
    ('2023-09-21', '2023-09-21', 1, 75000),
    ('2023-09-22', '2023-09-22', 1, 80000),
    ('2023-09-23', '2023-09-23', 1, 90000),
    ('2023-09-24', '2023-09-24', 1, 95000),
    ('2023-09-25', '2023-09-25', 1, 110000),
    ('2023-09-26', '2023-09-26', 1, 120000),
    ('2023-09-27', '2023-09-27', 1, 140000),
    ('2023-09-28', '2023-09-28', 1, 160000),
    ('2023-09-29', '2023-09-29', 1, 180000),
    ('2023-09-30', '2023-09-30', 1, 200000);