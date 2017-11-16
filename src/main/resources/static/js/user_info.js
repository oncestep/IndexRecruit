var url_type = GetQueryString("type");

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/user/info",
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);

            /**
             * 设置header栏头像信息
             */
            if (data.user != null) {
                header.type = 'user';
                header.person.user = data.user;
            } else if (data.hr != null) {
                header.type = 'hr';
                header.person.hr = data.hr;
            }

            /**
             * 设置个人资料input框默认原有值
             */
            //账户手机号
            $("#tel").val(data.user.mobile);
            //昵称
            $("#nickname").val(data.user.nickname);
            //邮箱
            $("#email").val(data.user.email);
            //实名
            $("#inputName").val(data.user.name);
            //性别
            $("#sex").val(data.user.gender);
            //出生年月
            $("#birthyear").val(data.user.birthYear);
            //毕业年份
            $("#graduateyear").val(data.user.graYear);
            //所在省市
            content.selectProv = data.user.province;
            content.getProv();
            content.selectCity = data.user.city;
            //最高学历
            $("#education").val(data.user.eduDegree);
            //学校名称
            $("#school").val(data.user.graduation);
            //专业名称
            $("#major").val(data.user.major);

            /**
             * 设置个人简历textarea框默认值
             */
            //专业能力
            $("#ability").val(data.resume.ability);
            //实习经历
            $("#internship").val(data.resume.internship);
            //工作经历
            $("#workExperience").val(data.resume.workExperience);
            //获奖荣誉
            $("#certificate").val(data.resume.certificate);
            //就职期望
            $("#jobDesire").val(data.resume.jobDesire);

            /**
             * vue对象绑定ajax返回数据
             */
            $(data.favorPosList).each(function (key, val) {
                // alert("收藏"+val.title);
                var down = val.salaryDown / 1000;
                var up = val.salaryUp / 1000;
                content.collections.push({
                    posId: val.positionId,
                    title: val.title,
                    salary: down + "K-" + up + "K",
                    place: val.workCity
                });
            });

            $(data.prePosList).each(function (key, val) {
                // alert("待处理"+val.title);
                var down = val.salaryDown / 1000;
                var up = val.salaryUp / 1000;
                content.boxs.push({
                    title: val.title,
                    salary: down + "K-" + up + "K",
                    place: val.workCity,
                    hrMobile: val.hrMobile,
                    hrEmail: val.hrEmail,
                    state: "申请待处理"
                });
            });

            $(data.applyPosList).each(function (key, val) {
                // alert("处理完成"+val.title);
                var down = val.salaryDown / 1000;
                var up = val.salaryUp / 1000;
                var handing = "申请待处理";
                if (val.state == 1) {
                    handing = "申请成功"
                } else if (val.state == 2) {
                    handing = "申请失效"
                } else {
                    handing = "申请待处理";
                }
                content.boxs.push({
                    title: val.title,
                    salary: down + "K-" + up + "K",
                    place: val.workCity,
                    state: handing
                });
            });

            $(data.allCategoryList).each(function (key, val) {
                content.categories.push({
                    categoryId: val.categoryId,
                    categoryName: val.categoryName
                });

                if (val.categoryId == data.user.dirDesire) {
                    content.selectCategory.categoryId = val.categoryId;
                    content.selectCategory.categoryName = val.categoryName;
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });
});

/**
 * 更新用户个人信息
 */
function infoUpdate() {

    $.ajax({
        type: "post",
        url: "http://localhost:8080/user/info/update",
        data: $("#user_info").serialize(),
        dataType: "text",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (msg) {
            console.log(msg);
        },
        error: function (msg) {
            console.log(msg);
        }
    });

    // $("#info_submit").attr("data-dismiss", "modal");
}


function posJump(){
    var id = $("#posId").val();
    window.location.href = "http://localhost:8080/position/"+id;
}

function posRemove(){
    var id = $("#posId").val();
    $.ajax({
        type:"get",
        url: "http://localhost:8080/user/disfavor/"+id,
        dataType:"text",
        success: function(msg){
            console.log(msg);
        },
        error: function(msg){
            console.log(msg);
        }
    });
    window.location.reload();
}


function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

var content = new Vue({
    el: '#vue-dom',
    data: {
        type: 'person',
        years: [1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000,
            2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016,
            2017, 2018, 2019, 2020, 2021],
        collections: [
            // {title: 'JAVA工程师', salary: '8K-15K', place: '杭州'},
            // {title: '数据库管理员', salary: '10K-18K', place: '青岛'},
            // {title: '导演助理', salary: '4K-9K', place: '新疆'}
        ],
        boxs: [
            // {title: 'JAVA工程师', salary: '8K-15K', place: '杭州', state: 'success'},
            // {title: '数据库管理员', salary: '10K-18K', place: '青岛', state: 'info'},
            // {title: '导演助理', salary: '4K-9K', place: '新疆', state: 'active'},
            // {title: '演员', salary: '1K-8K', place: '晋江', state: 'warning'},
            // {title: '乐视会计', salary: '24K-32K', place: '北京', state: 'warning'},
            // {title: '网易运营专员', salary: '16K-29K', place: '深圳', state: 'active'}
        ],
        categories: [],
        selectCategory: {
            categoryId: "",
            categoryName: ""
        },
        provs: [
            {label: "北京市", value: "北京市"},
            {label: "天津市", value: "天津市"},
            {label: "河北省", value: "河北省"},
            {label: "山西省", value: "山西省"},
            {label: "内蒙古自治区", value: "内蒙古自治区"},
            {label: "辽宁省", value: "辽宁省"},
            {label: "吉林省", value: "吉林省"},
            {label: "黑龙江省", value: "黑龙江省"},
            {label: "上海市", value: "上海市"},
            {label: "江苏省", value: "江苏省"},
            {label: "浙江省", value: "浙江省"},
            {label: "安徽省", value: "安徽省"},
            {label: "福建省", value: "福建省"},
            {label: "江西省", value: "江西省"},
            {label: "山东省", value: "山东省"},
            {label: "河南省", value: "河南省"},
            {label: "湖北省", value: "湖北省"},
            {label: "湖南省", value: "湖南省"},
            {label: "广东省", value: "广东省"},
            {label: "广西壮族自治区", value: "广西壮族自治区"},
            {label: "海南省", value: "海南省"},
            {label: "重庆市", value: "重庆市"},
            {label: "四川省", value: "四川省"},
            {label: "贵州省", value: "贵州省"},
            {label: "云南省", value: "云南省"},
            {label: "西藏自治区", value: "西藏自治区"},
            {label: "陕西省", value: "陕西省"},
            {label: "甘肃省", value: "甘肃省"},
            {label: "青海省", value: "青海省"},
            {label: "宁夏回族自治区", value: "宁夏回族自治区"},
            {label: "新疆维吾尔自治区", value: "新疆维吾尔自治区"},
            {label: "台湾省", value: "台湾省"},
            {label: "香港特别行政区", value: "香港特别行政区"},
            {label: "澳门特别行政区", value: "澳门特别行政区"}],
        cities: [],
        selectProv: '',
        selectCity: ''
    },
    mounted: function () {
        this.$nextTick(function () {

            if (GetQueryString('type') != null) {
                content.type = GetQueryString('type');
            } else {
                content.type = 'person';
            }
        })
    },
    computed: {
        navText: function () {
            if (this.type == 'person') {
                return "个人资料设置";
            } else if (this.type == 'box') {
                return "投件箱";
            } else if (this.type == 'collection') {
                return "收藏夹";
            } else if (this.type == 'resume') {
                return "个人简历";
            }
            return "个人中心";
        }
    },
    methods: {

        /*实现职位页面跳转*/
        // review: function(posId){
        //     return "$.jump(\'http://localhost:8080/position/"+posId+"\')";
        // },

        /*二级联动选择地区*/
        getProv: function () {
            var tempCity = [];
            content.selectCity = '';
            var allCity = [{
                prov: "北京市",
                label: "北京市"
            }, {
                prov: "天津市",
                label: "天津市"
            }, {
                prov: "河北省",
                label: "石家庄市"
            }, {
                prov: "河北省",
                label: "唐山市"
            }, {
                prov: "河北省",
                label: "秦皇岛市"
            }, {
                prov: "河北省",
                label: "邯郸市"
            }, {
                prov: "河北省",
                label: "邢台市"
            }, {
                prov: "河北省",
                label: "保定市"
            }, {
                prov: "河北省",
                label: "张家口市"
            }, {
                prov: "河北省",
                label: "承德市"
            }, {
                prov: "河北省",
                label: "沧州市"
            }, {
                prov: "河北省",
                label: "廊坊市"
            }, {
                prov: "河北省",
                label: "衡水市"
            }, {
                prov: "山西省",
                label: "太原市"
            }, {
                prov: "山西省",
                label: "大同市"
            }, {
                prov: "山西省",
                label: "阳泉市"
            }, {
                prov: "山西省",
                label: "长治市"
            }, {
                prov: "山西省",
                label: "晋城市"
            }, {
                prov: "山西省",
                label: "朔州市"
            }, {
                prov: "山西省",
                label: "晋中市"
            }, {
                prov: "山西省",
                label: "运城市"
            }, {
                prov: "山西省",
                label: "忻州市"
            }, {
                prov: "山西省",
                label: "临汾市"
            }, {
                prov: "山西省",
                label: "吕梁市"
            }, {
                prov: "内蒙古自治区",
                label: "呼和浩特市"
            }, {
                prov: "内蒙古自治区",
                label: "包头市"
            }, {
                prov: "内蒙古自治区",
                label: "乌海市"
            }, {
                prov: "内蒙古自治区",
                label: "赤峰市"
            }, {
                prov: "内蒙古自治区",
                label: "通辽市"
            }, {
                prov: "内蒙古自治区",
                label: "鄂尔多斯市"
            }, {
                prov: "内蒙古自治区",
                label: "呼伦贝尔市"
            }, {
                prov: "内蒙古自治区",
                label: "巴彦淖尔市"
            }, {
                prov: "内蒙古自治区",
                label: "乌兰察布市"
            }, {
                prov: "内蒙古自治区",
                label: "兴安盟"
            }, {
                prov: "内蒙古自治区",
                label: "锡林郭勒盟"
            }, {
                prov: "内蒙古自治区",
                label: "阿拉善盟"
            }, {
                prov: "辽宁省",
                label: "沈阳市"
            }, {
                prov: "辽宁省",
                label: "大连市"
            }, {
                prov: "辽宁省",
                label: "鞍山市"
            }, {
                prov: "辽宁省",
                label: "抚顺市"
            }, {
                prov: "辽宁省",
                label: "本溪市"
            }, {
                prov: "辽宁省",
                label: "丹东市"
            }, {
                prov: "辽宁省",
                label: "锦州市"
            }, {
                prov: "辽宁省",
                label: "营口市"
            }, {
                prov: "辽宁省",
                label: "阜新市"
            }, {
                prov: "辽宁省",
                label: "辽阳市"
            }, {
                prov: "辽宁省",
                label: "盘锦市"
            }, {
                prov: "辽宁省",
                label: "铁岭市"
            }, {
                prov: "辽宁省",
                label: "朝阳市"
            }, {
                prov: "辽宁省",
                label: "葫芦岛市"
            }, {
                prov: "吉林省",
                label: "长春市"
            }, {
                prov: "吉林省",
                label: "吉林市"
            }, {
                prov: "吉林省",
                label: "四平市"
            }, {
                prov: "吉林省",
                label: "辽源市"
            }, {
                prov: "吉林省",
                label: "通化市"
            }, {
                prov: "吉林省",
                label: "白山市"
            }, {
                prov: "吉林省",
                label: "松原市"
            }, {
                prov: "吉林省",
                label: "白城市"
            }, {
                prov: "吉林省",
                label: "延边朝鲜族自治州"
            }, {
                prov: "黑龙江省",
                label: "哈尔滨市"
            }, {
                prov: "黑龙江省",
                label: "齐齐哈尔市"
            }, {
                prov: "黑龙江省",
                label: "鸡西市"
            }, {
                prov: "黑龙江省",
                label: "鹤岗市"
            }, {
                prov: "黑龙江省",
                label: "双鸭山市"
            }, {
                prov: "黑龙江省",
                label: "大庆市"
            }, {
                prov: "黑龙江省",
                label: "伊春市"
            }, {
                prov: "黑龙江省",
                label: "佳木斯市"
            }, {
                prov: "黑龙江省",
                label: "七台河市"
            }, {
                prov: "黑龙江省",
                label: "牡丹江市"
            }, {
                prov: "黑龙江省",
                label: "黑河市"
            }, {
                prov: "黑龙江省",
                label: "绥化市"
            }, {
                prov: "黑龙江省",
                label: "大兴安岭地区"
            }, {
                prov: "上海市",
                label: "上海市"
            }, {
                prov: "江苏省",
                label: "南京市"
            }, {
                prov: "江苏省",
                label: "无锡市"
            }, {
                prov: "江苏省",
                label: "徐州市"
            }, {
                prov: "江苏省",
                label: "常州市"
            }, {
                prov: "江苏省",
                label: "苏州市"
            }, {
                prov: "江苏省",
                label: "南通市"
            }, {
                prov: "江苏省",
                label: "连云港市"
            }, {
                prov: "江苏省",
                label: "淮安市"
            }, {
                prov: "江苏省",
                label: "盐城市"
            }, {
                prov: "江苏省",
                label: "扬州市"
            }, {
                prov: "江苏省",
                label: "镇江市"
            }, {
                prov: "江苏省",
                label: "泰州市"
            }, {
                prov: "江苏省",
                label: "宿迁市"
            }, {
                prov: "浙江省",
                label: "杭州市"
            }, {
                prov: "浙江省",
                label: "宁波市"
            }, {
                prov: "浙江省",
                label: "温州市"
            }, {
                prov: "浙江省",
                label: "嘉兴市"
            }, {
                prov: "浙江省",
                label: "湖州市"
            }, {
                prov: "浙江省",
                label: "绍兴市"
            }, {
                prov: "浙江省",
                label: "金华市"
            }, {
                prov: "浙江省",
                label: "衢州市"
            }, {
                prov: "浙江省",
                label: "舟山市"
            }, {
                prov: "浙江省",
                label: "台州市"
            }, {
                prov: "浙江省",
                label: "丽水市"
            }, {
                prov: "安徽省",
                label: "合肥市"
            }, {
                prov: "安徽省",
                label: "芜湖市"
            }, {
                prov: "安徽省",
                label: "蚌埠市"
            }, {
                prov: "安徽省",
                label: "淮南市"
            }, {
                prov: "安徽省",
                label: "马鞍山市"
            }, {
                prov: "安徽省",
                label: "淮北市"
            }, {
                prov: "安徽省",
                label: "铜陵市"
            }, {
                prov: "安徽省",
                label: "安庆市"
            }, {
                prov: "安徽省",
                label: "黄山市"
            }, {
                prov: "安徽省",
                label: "滁州市"
            }, {
                prov: "安徽省",
                label: "阜阳市"
            }, {
                prov: "安徽省",
                label: "宿州市"
            }, {
                prov: "安徽省",
                label: "六安市"
            }, {
                prov: "安徽省",
                label: "亳州市"
            }, {
                prov: "安徽省",
                label: "池州市"
            }, {
                prov: "安徽省",
                label: "宣城市"
            }, {
                prov: "福建省",
                label: "福州市"
            }, {
                prov: "福建省",
                label: "厦门市"
            }, {
                prov: "福建省",
                label: "莆田市"
            }, {
                prov: "福建省",
                label: "三明市"
            }, {
                prov: "福建省",
                label: "泉州市"
            }, {
                prov: "福建省",
                label: "漳州市"
            }, {
                prov: "福建省",
                label: "南平市"
            }, {
                prov: "福建省",
                label: "龙岩市"
            }, {
                prov: "福建省",
                label: "宁德市"
            }, {
                prov: "江西省",
                label: "南昌市"
            }, {
                prov: "江西省",
                label: "景德镇市"
            }, {
                prov: "江西省",
                label: "萍乡市"
            }, {
                prov: "江西省",
                label: "九江市"
            }, {
                prov: "江西省",
                label: "新余市"
            }, {
                prov: "江西省",
                label: "鹰潭市"
            }, {
                prov: "江西省",
                label: "赣州市"
            }, {
                prov: "江西省",
                label: "吉安市"
            }, {
                prov: "江西省",
                label: "宜春市"
            }, {
                prov: "江西省",
                label: "抚州市"
            }, {
                prov: "江西省",
                label: "上饶市"
            }, {
                prov: "山东省",
                label: "济南市"
            }, {
                prov: "山东省",
                label: "青岛市"
            }, {
                prov: "山东省",
                label: "淄博市"
            }, {
                prov: "山东省",
                label: "枣庄市"
            }, {
                prov: "山东省",
                label: "东营市"
            }, {
                prov: "山东省",
                label: "烟台市"
            }, {
                prov: "山东省",
                label: "潍坊市"
            }, {
                prov: "山东省",
                label: "济宁市"
            }, {
                prov: "山东省",
                label: "泰安市"
            }, {
                prov: "山东省",
                label: "威海市"
            }, {
                prov: "山东省",
                label: "日照市"
            }, {
                prov: "山东省",
                label: "莱芜市"
            }, {
                prov: "山东省",
                label: "临沂市"
            }, {
                prov: "山东省",
                label: "德州市"
            }, {
                prov: "山东省",
                label: "聊城市"
            }, {
                prov: "山东省",
                label: "滨州市"
            }, {
                prov: "山东省",
                label: "菏泽市"
            }, {
                prov: "河南省",
                label: "郑州市"
            }, {
                prov: "河南省",
                label: "开封市"
            }, {
                prov: "河南省",
                label: "洛阳市"
            }, {
                prov: "河南省",
                label: "平顶山市"
            }, {
                prov: "河南省",
                label: "安阳市"
            }, {
                prov: "河南省",
                label: "鹤壁市"
            }, {
                prov: "河南省",
                label: "新乡市"
            }, {
                prov: "河南省",
                label: "焦作市"
            }, {
                prov: "河南省",
                label: "濮阳市"
            }, {
                prov: "河南省",
                label: "许昌市"
            }, {
                prov: "河南省",
                label: "漯河市"
            }, {
                prov: "河南省",
                label: "三门峡市"
            }, {
                prov: "河南省",
                label: "南阳市"
            }, {
                prov: "河南省",
                label: "商丘市"
            }, {
                prov: "河南省",
                label: "信阳市"
            }, {
                prov: "河南省",
                label: "周口市"
            }, {
                prov: "河南省",
                label: "驻马店市"
            }, {
                prov: "河南省",
                label: "省直辖县级行政区划"
            }, {
                prov: "湖北省",
                label: "武汉市"
            }, {
                prov: "湖北省",
                label: "黄石市"
            }, {
                prov: "湖北省",
                label: "十堰市"
            }, {
                prov: "湖北省",
                label: "宜昌市"
            }, {
                prov: "湖北省",
                label: "襄阳市"
            }, {
                prov: "湖北省",
                label: "鄂州市"
            }, {
                prov: "湖北省",
                label: "荆门市"
            }, {
                prov: "湖北省",
                label: "孝感市"
            }, {
                prov: "湖北省",
                label: "荆州市"
            }, {
                prov: "湖北省",
                label: "黄冈市"
            }, {
                prov: "湖北省",
                label: "咸宁市"
            }, {
                prov: "湖北省",
                label: "随州市"
            }, {
                prov: "湖北省",
                label: "恩施土家族苗族自治州"
            }, {
                prov: "湖北省",
                label: "省直辖县级行政区划"
            }, {
                prov: "湖北省",
                label: "仙桃市"
            }, {
                prov: "湖北省",
                label: "潜江市"
            }, {
                prov: "湖北省",
                label: "天门市"
            }, {
                prov: "湖北省",
                label: "神农架林区"
            }, {
                prov: "湖南省",
                label: "长沙市"
            }, {
                prov: "湖南省",
                label: "株洲市"
            }, {
                prov: "湖南省",
                label: "湘潭市"
            }, {
                prov: "湖南省",
                label: "衡阳市"
            }, {
                prov: "湖南省",
                label: "邵阳市"
            }, {
                prov: "湖南省",
                label: "岳阳市"
            }, {
                prov: "湖南省",
                label: "常德市"
            }, {
                prov: "湖南省",
                label: "张家界市"
            }, {
                prov: "湖南省",
                label: "益阳市"
            }, {
                prov: "湖南省",
                label: "郴州市"
            }, {
                prov: "湖南省",
                label: "永州市"
            }, {
                prov: "湖南省",
                label: "怀化市"
            }, {
                prov: "湖南省",
                label: "娄底市"
            }, {
                prov: "湖南省",
                label: "湘西土家族苗族自治州"
            }, {
                prov: "广东省",
                label: "广州市"
            }, {
                prov: "广东省",
                label: "韶关市"
            }, {
                prov: "广东省",
                label: "深圳市"
            }, {
                prov: "广东省",
                label: "珠海市"
            }, {
                prov: "广东省",
                label: "汕头市"
            }, {
                prov: "广东省",
                label: "佛山市"
            }, {
                prov: "广东省",
                label: "江门市"
            }, {
                prov: "广东省",
                label: "湛江市"
            }, {
                prov: "广东省",
                label: "茂名市"
            }, {
                prov: "广东省",
                label: "肇庆市"
            }, {
                prov: "广东省",
                label: "惠州市"
            }, {
                prov: "广东省",
                label: "梅州市"
            }, {
                prov: "广东省",
                label: "汕尾市"
            }, {
                prov: "广东省",
                label: "河源市"
            }, {
                prov: "广东省",
                label: "阳江市"
            }, {
                prov: "广东省",
                label: "清远市"
            }, {
                prov: "广东省",
                label: "东莞市"
            }, {
                prov: "广东省",
                label: "中山市"
            }, {
                prov: "广东省",
                label: "潮州市"
            }, {
                prov: "广东省",
                label: "揭阳市"
            }, {
                prov: "广东省",
                label: "云浮市"
            }, {
                prov: "广西壮族自治区",
                label: "南宁市"
            }, {
                prov: "广西壮族自治区",
                label: "柳州市"
            }, {
                prov: "广西壮族自治区",
                label: "桂林市"
            }, {
                prov: "广西壮族自治区",
                label: "梧州市"
            }, {
                prov: "广西壮族自治区",
                label: "北海市"
            }, {
                prov: "广西壮族自治区",
                label: "防城港市"
            }, {
                prov: "广西壮族自治区",
                label: "钦州市"
            }, {
                prov: "广西壮族自治区",
                label: "贵港市"
            }, {
                prov: "广西壮族自治区",
                label: "玉林市"
            }, {
                prov: "广西壮族自治区",
                label: "百色市"
            }, {
                prov: "广西壮族自治区",
                label: "贺州市"
            }, {
                prov: "广西壮族自治区",
                label: "河池市"
            }, {
                prov: "广西壮族自治区",
                label: "来宾市"
            }, {
                prov: "广西壮族自治区",
                label: "崇左市"
            }, {
                prov: "海南省",
                label: "海口市"
            }, {
                prov: "海南省",
                label: "三亚市"
            }, {
                prov: "海南省",
                label: "三沙市"
            }, {
                prov: "海南省",
                label: "省直辖县级行政区划"
            }, {
                prov: "海南省",
                label: "五指山市"
            }, {
                prov: "海南省",
                label: "琼海市"
            }, {
                prov: "海南省",
                label: "儋州市"
            }, {
                prov: "海南省",
                label: "文昌市"
            }, {
                prov: "海南省",
                label: "万宁市"
            }, {
                prov: "海南省",
                label: "东方市"
            }, {
                prov: "海南省",
                label: "定安县"
            }, {
                prov: "海南省",
                label: "屯昌县"
            }, {
                prov: "海南省",
                label: "澄迈县"
            }, {
                prov: "海南省",
                label: "临高县"
            }, {
                prov: "海南省",
                label: "白沙黎族自治县"
            }, {
                prov: "海南省",
                label: "昌江黎族自治县"
            }, {
                prov: "海南省",
                label: "乐东黎族自治县"
            }, {
                prov: "海南省",
                label: "陵水黎族自治县"
            }, {
                prov: "海南省",
                label: "保亭黎族苗族自治县"
            }, {
                prov: "海南省",
                label: "琼中黎族苗族自治县"
            }, {
                prov: "重庆市",
                label: "重庆市"
            }, {
                prov: "四川省",
                label: "成都市"
            }, {
                prov: "四川省",
                label: "自贡市"
            }, {
                prov: "四川省",
                label: "攀枝花市"
            }, {
                prov: "四川省",
                label: "泸州市"
            }, {
                prov: "四川省",
                label: "德阳市"
            }, {
                prov: "四川省",
                label: "绵阳市"
            }, {
                prov: "四川省",
                label: "广元市"
            }, {
                prov: "四川省",
                label: "遂宁市"
            }, {
                prov: "四川省",
                label: "内江市"
            }, {
                prov: "四川省",
                label: "乐山市"
            }, {
                prov: "四川省",
                label: "南充市"
            }, {
                prov: "四川省",
                label: "眉山市"
            }, {
                prov: "四川省",
                label: "宜宾市"
            }, {
                prov: "四川省",
                label: "广安市"
            }, {
                prov: "四川省",
                label: "达州市"
            }, {
                prov: "四川省",
                label: "雅安市"
            }, {
                prov: "四川省",
                label: "巴中市"
            }, {
                prov: "四川省",
                label: "资阳市"
            }, {
                prov: "四川省",
                label: "阿坝藏族羌族自治州"
            }, {
                prov: "四川省",
                label: "甘孜藏族自治州"
            }, {
                prov: "四川省",
                label: "凉山彝族自治州"
            }, {
                prov: "贵州省",
                label: "贵阳市"
            }, {
                prov: "贵州省",
                label: "六盘水市"
            }, {
                prov: "贵州省",
                label: "遵义市"
            }, {
                prov: "贵州省",
                label: "安顺市"
            }, {
                prov: "贵州省",
                label: "毕节市"
            }, {
                prov: "贵州省",
                label: "铜仁市"
            }, {
                prov: "贵州省",
                label: "黔西南布依族苗族自治州"
            }, {
                prov: "贵州省",
                label: "黔东南苗族侗族自治州"
            }, {
                prov: "贵州省",
                label: "黔南布依族苗族自治州"
            }, {
                prov: "云南省",
                label: "昆明市"
            }, {
                prov: "云南省",
                label: "曲靖市"
            }, {
                prov: "云南省",
                label: "玉溪市"
            }, {
                prov: "云南省",
                label: "保山市"
            }, {
                prov: "云南省",
                label: "昭通市"
            }, {
                prov: "云南省",
                label: "丽江市"
            }, {
                prov: "云南省",
                label: "普洱市"
            }, {
                prov: "云南省",
                label: "临沧市"
            }, {
                prov: "云南省",
                label: "楚雄彝族自治州"
            }, {
                prov: "云南省",
                label: "红河哈尼族彝族自治州"
            }, {
                prov: "云南省",
                label: "文山壮族苗族自治州"
            }, {
                prov: "云南省",
                label: "西双版纳傣族自治州"
            }, {
                prov: "云南省",
                label: "大理白族自治州"
            }, {
                prov: "云南省",
                label: "德宏傣族景颇族自治州"
            }, {
                prov: "云南省",
                label: "怒江傈僳族自治州"
            }, {
                prov: "云南省",
                label: "迪庆藏族自治州"
            }, {
                prov: "西藏自治区",
                label: "拉萨市"
            }, {
                prov: "西藏自治区",
                label: "昌都地区"
            }, {
                prov: "西藏自治区",
                label: "山南地区"
            }, {
                prov: "西藏自治区",
                label: "日喀则地区"
            }, {
                prov: "西藏自治区",
                label: "那曲地区"
            }, {
                prov: "西藏自治区",
                label: "阿里地区"
            }, {
                prov: "西藏自治区",
                label: "林芝地区"
            }, {
                prov: "陕西省",
                label: "西安市"
            }, {
                prov: "陕西省",
                label: "铜川市"
            }, {
                prov: "陕西省",
                label: "宝鸡市"
            }, {
                prov: "陕西省",
                label: "咸阳市"
            }, {
                prov: "陕西省",
                label: "渭南市"
            }, {
                prov: "陕西省",
                label: "延安市"
            }, {
                prov: "陕西省",
                label: "汉中市"
            }, {
                prov: "陕西省",
                label: "榆林市"
            }, {
                prov: "陕西省",
                label: "安康市"
            }, {
                prov: "陕西省",
                label: "商洛市"
            }, {
                prov: "甘肃省",
                label: "兰州市"
            }, {
                prov: "甘肃省",
                label: "嘉峪关市"
            }, {
                prov: "甘肃省",
                label: "金昌市"
            }, {
                prov: "甘肃省",
                label: "白银市"
            }, {
                prov: "甘肃省",
                label: "天水市"
            }, {
                prov: "甘肃省",
                label: "武威市"
            }, {
                prov: "甘肃省",
                label: "张掖市"
            }, {
                prov: "甘肃省",
                label: "平凉市"
            }, {
                prov: "甘肃省",
                label: "酒泉市"
            }, {
                prov: "甘肃省",
                label: "庆阳市"
            }, {
                prov: "甘肃省",
                label: "定西市"
            }, {
                prov: "甘肃省",
                label: "陇南市"
            }, {
                prov: "甘肃省",
                label: "临夏回族自治州"
            }, {
                prov: "甘肃省",
                label: "甘南藏族自治州"
            }, {
                prov: "青海省",
                label: "西宁市"
            }, {
                prov: "青海省",
                label: "海东市"
            }, {
                prov: "青海省",
                label: "海北藏族自治州"
            }, {
                prov: "青海省",
                label: "黄南藏族自治州"
            }, {
                prov: "青海省",
                label: "海南藏族自治州"
            }, {
                prov: "青海省",
                label: "果洛藏族自治州"
            }, {
                prov: "青海省",
                label: "玉树藏族自治州"
            }, {
                prov: "青海省",
                label: "海西蒙古族藏族自治州"
            }, {
                prov: "宁夏回族自治区",
                label: "银川市"
            }, {
                prov: "宁夏回族自治区",
                label: "石嘴山市"
            }, {
                prov: "宁夏回族自治区",
                label: "吴忠市"
            }, {
                prov: "宁夏回族自治区",
                label: "固原市"
            }, {
                prov: "宁夏回族自治区",
                label: "中卫市"
            }, {
                prov: "新疆维吾尔自治区",
                label: "乌鲁木齐市"
            }, {
                prov: "新疆维吾尔自治区",
                label: "克拉玛依市"
            }, {
                prov: "新疆维吾尔自治区",
                label: "吐鲁番地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "哈密地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "昌吉回族自治州"
            }, {
                prov: "新疆维吾尔自治区",
                label: "博尔塔拉蒙古自治州"
            }, {
                prov: "新疆维吾尔自治区",
                label: "巴音郭楞蒙古自治州"
            }, {
                prov: "新疆维吾尔自治区",
                label: "阿克苏地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "克孜勒苏柯尔克孜自治州"
            }, {
                prov: "新疆维吾尔自治区",
                label: "喀什地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "和田地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "伊犁哈萨克自治州"
            }, {
                prov: "新疆维吾尔自治区",
                label: "塔城地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "阿勒泰地区"
            }, {
                prov: "新疆维吾尔自治区",
                label: "自治区直辖县级行政区划"
            }, {
                prov: "新疆维吾尔自治区",
                label: "石河子市"
            }, {
                prov: "新疆维吾尔自治区",
                label: "阿拉尔市"
            }, {
                prov: "新疆维吾尔自治区",
                label: "图木舒克市"
            }, {
                prov: "新疆维吾尔自治区",
                label: "五家渠市"
            }, {
                prov: "台湾省",
                label: "台北市"
            }, {
                prov: "台湾省",
                label: "高雄市"
            }, {
                prov: "台湾省",
                label: "基隆市"
            }, {
                prov: "台湾省",
                label: "台中市"
            }, {
                prov: "台湾省",
                label: "台南市"
            }, {
                prov: "台湾省",
                label: "新竹市"
            }, {
                prov: "台湾省",
                label: "嘉义市"
            }, {
                prov: "台湾省",
                label: "省直辖"
            }, {
                prov: "香港特别行政区",
                label: "香港岛"
            }, {
                prov: "香港特别行政区",
                label: "九龙"
            }, {
                prov: "香港特别行政区",
                label: "新界"
            }, {
                prov: "澳门特别行政区",
                label: "澳门半岛"
            }, {
                prov: "澳门特别行政区",
                label: "澳门离岛"
            }, {
                prov: "澳门特别行政区",
                label: "无堂区划分区域"
            }];
            for (var i = 0; i < allCity.length; i++) {
                if (content.selectProv == allCity[i].prov) {
                    tempCity.push({label: allCity[i].label, value: allCity[i].label})
                }
            }
            content.cities = tempCity;
        },


    }
});





