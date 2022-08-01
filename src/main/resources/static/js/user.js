let index = {
    init: function () {
        // jQuery 사용
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#mail_send").on("click", () => {
            this.send();
        })
    },
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            birthday: $("#birthday").val(),
            email: $("#email").val(),
            phoneNumber: $("#phoneNumber").val(),
            address: $("#address").val(),
            inputCheckSum : $('#inputCheckSum').val()
        }
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if(res.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    send: function () {
        alert("인증번호가 이메일로 전송되었습니다");
        let data = {
            email: $("#email").val(),
        }
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProcEmail",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            if(res.status === 500) {
                alert("전송 실패.");
            } else {
                alert("이메일 전송.");
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
}

index.init();

