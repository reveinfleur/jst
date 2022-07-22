let index = {
    init: function () {
        // jQuery 사용
        $("#btn-modify").on("click", () => {
            this.update(); // save함수 이벤트로 호출
        });
        $("#btn-delete").on("click", () => {
            this.delete();
        });
    },
    update: function () {
        let data = {
            id: $("#id").val(),
            title: $("#title").val(),
            content: $("#content").val()
        }
        console.log("save들어옴");
        $.ajax({
            // 회원가입 수행 요청
            type: "PUT",
            url: "/board/modify",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if (res.status === 500) {
                alert("글쓰기 실패하였습니다.");
            } else {
                alert("글쓰기 완료되었습니다.");
                location.href = "/board/CustomerServiceBoard";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    delete: function () {
        let data = {
            id: $("#boardId").text()
        }
        $.ajax({
            // 회원가입 수행 요청
            type: "DELETE",
            url: "/board/delete"+id,
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if(res.status === 500) {
                alert("삭제 실패하였습니다.");
            } else {
                alert("삭제 완료되었습니다.");
                location.href = "/board/CustomerServiceBoard";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

}

index.init();

