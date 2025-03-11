//json 데이터로 만들어 main 변수에 넣어줍니다.
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function() {
            // id='btn-save' 설정되어있은 것을 클릭했을때 이곳이 실행됩니다.
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';// 글 등록 후 메인페이지로 이동
        }).fail(function(error) {
            // 등록이 실패했을 때 error 변수에 내용이 담겨져 이곳으로 옵니다.
            alert(JSON.stringify(error));
        });
        // JSON.stringify() ==> json데이터를 string으로 변환해 줍니다.
    }
};

main.init();