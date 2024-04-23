<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="util.js"></script>
</head>
<style>
    /* 톡상담 버튼 위치 스타일 */

    #kakao-talk-channel-chat-button {
        position: fixed;
        z-index: 999;
        right: 30px;
        bottom: 30px;

    }

    #aboutMent {

        color: white;
        font-size: larger;
    }
</style>
<body>
 <header id="header"></header>



    <main>
        <!-- 소개말 -->
        <section class="text-center mb-5 justify-content-center" id="aboutMent">
            <div style="background-image: url('./품종추천이미지/센터소개.jpg');        
            background-size: cover;
            background-repeat: no-repeat;width: 100vw;
    height: 50vh;">
                
                <p class="fs-1 fw-bold py-5"> <br>올바른 반려동물 문화를 지향하는 <span style="border-bottom: 1px solid white;">펫토피아</span> 입니다.</p>


                <p class="fs-5 fw-bold">펫토피아는 펫(pet) + 유토피아(utopia)의 합성어로 <strong style="color: white; border-bottom:1px solid white ;">'유기동물들을 위한 작은 천국'</strong>이라는 의미를 가지고
                    있습니다.

                </p>
                <p class="py-1 fw-bold">
                    펫토피아는 사랑과 따뜻함으로 가득한 곳으로 버려진 동물들에게 새로운 가족을 찾아주는 보호 센터입니다.<br>
                    동물과 사람 모두의 꿈이 현실이 되고 함께하는 모든 순간이 특별한 기억으로 남을 수 있도록 끊임없이 고민하고 있습니다. <br>
                    항상 처음과 같이 곧은 길로 나아가고자 지속적인 노력을 기울이고 있습니다.
                </p>
            </div>
        </section>






        <!-- 소개 사진 -->
        <!-- 구조 사진, 시설 사진, 페스티벌 사진 -->
        <section style="margin-top: 5%;">
            <div class="container text-center mt-5 mb-5">
                <div class="row">
                    <div class="col">
                        <p class="fs-4 fw-bold">CARE</p>
                        <img src="data:image/jpeg;base64,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"
                            alt="입양 활성화" class="rounded-5">
                        <p style="font-size: smaller;" class="mt-2"> 보호동물 입양 활성화 </p>
                    </div>
                    <div class="col">
                        <p class="fs-4 fw-bold">ENVIROMENT</p>
                        <img src="https://mobile.busan.com/nas/wcms/wcms_data/photos/2019/04/30/2019043017410495169_l.jpg"
                            alt="펫토피아 시설" class="rounded-5" style="width: 300px; height: 168px;">
                        <p style="font-size: smaller;" class="mt-2">시설 사진</p>
                    </div>
                    <div class="col">
                        <p class="fs-4 fw-bold">FESTIVAL</p>
                        <img src="https://cdn.joongboo.com/news/photo/201810/1295880_2012416_5241.png"
                            alt="산학관 협력사업 및 행사 추진" class="rounded-5" style="width: 300px; height: 168px;">
                        <p style="font-size: smaller;" class="mt-2"> 올바른 반려문화 형성을 위한 행사 추진</p>
                    </div>
                </div>
            </div>
        </section>



    </main>

    <footer>
        <div id="kakao-talk-channel-chat-button"></div>
    </footer>

</body>
</html>