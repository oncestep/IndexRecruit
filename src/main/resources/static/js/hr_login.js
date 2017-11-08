var form =new Vue({
    el:'#loginForm',
    data:{
        url:'/hr/hr_loginPost',
        form:{
            userName: '',
            userPass: ''
        }
    },
    methods:{
        login : function () {
            this.$http.post(this.url, this.form).then((message) => {
                alert("success");
            }).
            catch((message) => {
                alert("false");
            });
        }
    }
});