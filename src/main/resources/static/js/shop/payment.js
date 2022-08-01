let index = {
    init: function () {
        // jQuery 사용
        $("#card_select").on("click", () => {
            this.save(); // save함수 이벤트로 호출
        });
        $("#pay_btn").on("click", () => {
            console.log("pay들어옴0");
            this.get() // save함수 이벤트로 호출
        });
    },
    save: function () {
        let data = {
            id: $("#selectBox").val(),
        }
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/shop/payment",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if (res.status === 500) {
                alert("저장 실패하였습니다.");
            } else {

            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    get: function () {
        console.log("들어옴");
        let data = {
            id : $("#shopId").text(),
            price: $("#shopPrice").text(),
            cardName: $("#card-holder").val(),
        }
        $.ajax({
            type: "POST",
            url: "/shop/payment/go",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if (res.status === 500) {
                alert("저장 실패하였습니다.");
            } else {
                location.href = "/shop/successForm";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}
index.init();

