var href = window.location.href;

var box = new Vue({
    el: '#position_content',
    data: {
        position: [{}],
        department: [{}],
        company: [{}],
        category: [{}],
        reviews: [{}]
    },
    created: function () {
        this.$nextTick(function () {
            $.ajax({
                url: href + "/1",
                type: "post",
                dataType: "json",
                success: function (msg) {
                    box.position = msg.position;
                    box.department = msg.department;
                    box.company = msg.company;
                    box.category = msg.category;

                    console.log(msg);

                    box.reviews.pop();
                    $(msg.comList.list).each(function (key, val) {
                        box.reviews.push({
                            userName: val.nickname,
                            reviewDetail: val.content.replace(/<[^>]+>/g,"")
                        });
                    });

                    if (msg.user != null) {
                        header.type = 'user';
                        header.person.user = msg.user;
                    } else if (msg.hr != null) {
                        header.type = 'hr';
                        header.person.hr = msg.hr;
                    }
                },
                error: function (msg) {
                    console.log(msg);
                }
            });
        });
    }

});


var favorFlag;
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/user/favorOrNot/" + posId,
        type: "get",
        dataType: "json",
        success: function (msg) {
            if (msg == "0") {
                $("#favor_tag").css("background-color", "#3992d6").css("border", "1px solid #3992d6").text("收藏");
                favorFlag = 0;
            } else {
                $("#favor_tag").css("background-color", "#707070").css("border", "1px solid #707070").text("取消收藏");
                favorFlag = 1;
            }
        },
        error: function (msg) {
            console.log(msg);
        }
    });

});

var index = href.lastIndexOf("\/");
var posId = href.substr(index + 1, href.length);
// document.getElementById("apply_tag").setAttribute("href","http://localhost:8080/user/apply/"+posId);
$("#apply_tag").attr("href", "http://localhost:8080/user/apply/" + posId);
$("#favor_tag").click(function () {
    if (favorFlag == 0) {
        $.ajax({
            url: "http://localhost:8080/user/favor/" + posId,
            type: "get",
            dataType: "text",
            success: function (msg) {
                if (msg == "success") {
                    $("#favor_tag").css("background-color", "#707070").css("border", "1px solid #707070").text("取消收藏");
                    favorFlag = 1;
                } else {
                    console.log(msg);
                }
            },
            error: function (msg) {
                console.log(msg);
            }
        });
        window.location.reload();
    } else {
        $.ajax({
            url: "http://localhost:8080/user/disfavor/" + posId,
            type: "get",
            dataType: "text",
            success: function (msg) {
                if (msg == "success") {
                    $("#favor_tag").css("background-color", "#3992d6").css("border", "1px solid #3992d6").text("收藏");
                    favorFlag = 0;
                } else {
                    console.log(msg)
                }
            },
            error: function (msg) {
                console.log(msg);
            }
        });

    }
});

//隐藏表单项提交
$("#posId").val(posId);


/**
 * 评论ajax表单提交
 */
// function comPublish() {
//
//     var type = $("#comment_star").val();
//     type = type > 0 ? type : 1;
//     var content = layedit.getText(index);
//     var input = {
//         posId: posId,
//         type: type,
//         content: content
//     }
//
//     alert(JSON.stringify(input));
//
//     $.ajax({
//         type: "post",
//         url: "http://localhost:8080/user/comment",
//         data: input,
//         dataType: "text",
//         success: function (msg) {
//             console.log(msg);
//         },
//         error: function (msg) {
//             console.log(msg);
//         }
//     });
//
//     window.location.href(href);
// }


