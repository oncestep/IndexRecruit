var page = 1;
var pageTotal = 5;

var box = new Vue({
    el: '#posItem',
    data: {
        orderBy: "salaryUp",
        keyword: "",
        jobs: [{}]
    },
    mounted: function () {

        this.$nextTick(function () {

            if (GetQueryString('orderBy') != null) {
                box.orderBy = GetQueryString('orderBy');
            } else {
                box.orderBy = 'salaryUp';
            }
            if (box.orderBy == "hits") {
                $("#byHits").parent().addClass("active");
            } else if (box.orderBy == "releaseDate") {
                $("#byRelease").parent().addClass("active");
            } else {
                $("#bySalary").parent().addClass("active");
            }

            box.keyword = decodeURIComponent(escape(GetQueryString('keyword')));
            $("#keyword").val(box.keyword);

            var searchItem = {
                keyword: box.keyword,
                orderBy: box.orderBy,
                page: 1
            };

            $.ajax({
                url: "http://localhost:8080/search",
                type: "post",
                data: searchItem,
                dataType: "json",
                success: function (data) {
                    pageTotal = data.posInfo.pages;
                    box.jobs = data.posInfo.list;

                    if (data.user != null) {
                        headervue.type = 'user';
                        headervue.person.user = data.user;
                    } else if (data.hr != null) {
                        headervue.type = 'hr';
                        headervue.person.hr = data.hr;
                    }

                },
                error: function (msg) {
                    console.log(msg);
                }
            });

        });
    }
});


$("#bySalary").click(function () {
    window.location.href = "?keyword=" + box.keyword + "&orderBy=salaryUp";
});

$("#byRelease").click(function () {
    window.location.href = "?keyword=" + box.keyword + "&orderBy=releaseDate";
});

$("#byHits").click(function () {
    window.location.href = "?keyword=" + box.keyword + "&orderBy=hits";
});

function nextPage() {
    page = page + 1;
    if (page <= pageTotal) {
        var searchItem = {
            keyword: box.keyword,
            orderBy: box.orderBy,
            page: page
        };
        $.ajax({
            url: "http://localhost:8080/search",
            type: "post",
            data: searchItem,
            dataType: "json",
            success: function (data) {

                $.each(data.posInfo.list, function (key, val) {
                    box.jobs.push(val);
                });

            }, error: function () {
                $("#viewMoreButton").empty();
                $("#viewMoreButton").append("没有更多");
            }
        });
    } else {
        $("#viewMoreButton").empty();
        $("#viewMoreButton").append("没有更多");
        $("#viewMoreButton").addClass("disable");
    }
}

//查询url中orderBy方式
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
