//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                } else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

        //以下为官方示例
        $().ready(function () {
            

            // validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#cform").validate({
                rules: {
                    companyName: "required",
                    city: {
						required:true,
						minlength: 2
					},                
                    companyCode: {
                        required: true,
                        rangelength: [6,6]
                    },                
                    description: {
                        required: true
                    },
                    phone: {
						required: true,
						digits:true
                    }
                },
                messages: {
                    companyName: icon + "请输入公司姓名",
                    city: {
                        required: icon + "请输入城市名",
                        minlength: icon + "未知城市"
                    },
                    companyCode: {
                        required: icon + "请输入公司对应HR码",
                        rangelength: icon + "只允许六位数HR码",
                    },
                    description: icon + "请输入公司详情",
                    phone: {
                        required: icon + "请输入公司联系方式",
                        digits: '联系方式格式错误'
                    }
                }
            });

        });
