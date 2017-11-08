var page = 1;
var pageTotal =5;
var box =new Vue({
    el:'#job-content',
    data:{
        jobs:[{
//                job_id:"2017001",
//                job_name:"大数据平台开发",
//                job_time:"两天前发布",
//                job_salary:"7K-14K",
//                job_require:"经验1-3年/本科",
//                job_tag:[
//                    {tag_name:"架构师"},
//                    {tag_name:"Hadoop"},
//                    {tag_name:"数据分析"}
//                ],
//                company:{
//                    company_id:"2017001",
//                    company_name:"中国移动",
//                    company_description:"移动互联网/成熟型",
//                    company_img:"http://static.lagou.com/thumbnail_100x100/i/image/M00/68/A7/CgqKkVgRYweAD4MtAAAzR3Jd4fM663.png"
//                }



        }]
    },
    mounted:function () {
        this.$nextTick(function () {
            $.ajax({
                url: "http://localhost:8080/user/page/"+ page,
                type: "post",
                dataType: "json",
//                    jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
//                    jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自
                success:function (msg) {

                    box.jobs = msg.posInfo;

                        if(msg.user!=null){
                            headervue.type='user';
                            headervue.person.user=msg.user;
                        }else if(msg.hr!=null){
                            headervue.type='hr';
                            headervue.person.hr=msg.hr;
                        }

                    // pageTotal = msg.posInfo.pages;
                },
                error:function(msg){
                    window.location.href = "localhost:8080/user/login";
                }
            });

        });
    }
});


function nextPage() {
    page = page+1;
    if(page<=pageTotal) {
        $.ajax({
            url: "http://localhost:8080/user/page/" + page,
            type: "post",
            dataType: "json",
            success: function (msg) {
                for(var i =0;i<msg.posInfo.length;i++){
                    box.jobs.push(msg.posInfo[i]);
                }
                // box.jobs.push(msg.posInfo);
            },error:function () {
                $("#viewMoreButton").empty();
                $("#viewMoreButton").append("没有更多");
            }
        });
    }else {
        $("#viewMoreButton").empty();
        $("#viewMoreButton").append("没有更多");


        $("#viewMoreButton").addClass("disable");
    }
}