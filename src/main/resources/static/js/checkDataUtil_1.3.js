/**
 * 数据校验JS说明，包含目前开发中一些常用检验方式和对输入框数据限制功能
 * @author sunfangwei,cyf
 * @version 1.1.0
 */

/**
 * 数据校验
 * @author sunfangwei
 * @type {{isNumber: function(*=): boolean, isEmpty: function(*): boolean, isEnglish: function(*=): boolean, isSpecialChar: function(*=): boolean, isMobilePhone: function(*=): boolean, isEmail: function(*=): boolean}}
 */
CheckDataUtil = {


    /**
     * 判断数字
     * @param number 数字
     * @returns {boolean} ture是数字，false不是数字
     */
    isNumber: function (number) {
        var number2 = Number(number) + "";
        if ("NaN" != number2) {
            return true
        }
        return false;
    },
    /**
     * 判断是否为空字符串
     * @param str
     * @returns {boolean} true空：false不为空
     */
    isEmpty: function (str) {
        var map = {};
        map[null] = true;
        map[""] = true;
        map[" "] = true;
        map["null"] = true;
        map["NULL"] = true;
        map["undefined"] = true;
        map[undefined] = true;
        str = String(str).replace(/\s+/g, "")//去除空格
        return map[str] ? true : false;
    },
    /**
     * 判断是否为空字符串
     * @param str
     * @returns {boolean} true空：false不为空
     */
    isEmptyPure: function (str) {
        var map = {};
        map[null] = true;
        map[""] = true;
        map[" "] = true;
        map["null"] = true;
        map["NULL"] = true;
        map["undefined"] = true;
        map[undefined] = true;
        return map[str] ? true : false;
    },
    /**
     * 判断是否为英文字母
     * @param str
     * @returns {boolean} true是，false不是
     */
    isEnglish: function (str) {
        var matchStr = /^[A-Za-z]+$/;
        return matchStr.test(str)
    },
    /**
     * 判断是否含有特殊字符
     * @param str
     * @return true为包含，false为不包含
     */
    isSpecialChar: function (str) {
        var matchStr = /[ _`~!@#$%^&*()+=|{}':;',\[\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\s\n\r\t]/
        return matchStr.test(str)
    },
    /**
     * 判断是否为手机号
     * @param phoneNumber 手机号
     * @returns {boolean} ture手机号，false不是正确的手机号
     */
    isMobilePhone: function (phoneNumber) {
        var matchStr = /^1([34578][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
        return matchStr.test(phoneNumber)
    },
    /**
     * 判断是否为邮箱
     * @param emailStr
     * @returns {boolean} true是，false不是
     */
    isEmail: function (emailStr) {
        var matchStr = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        return matchStr.test(emailStr);
    },
    /**
     * 判断是否只包含字母或数字
     * @param str 字符串
     * @returns {boolean} true是字母或数字  false非字母或数字
     */
    isEnglishAndNumber: function (str) {
        var matchStr = /^[0-9a-zA-Z]+$/
        if (CheckDataUtil.isEnglish(str)) {
            return true;
        }
        if (CheckDataUtil.isNumber(str)) {
            return true;
        }
        if (matchStr.test(str)) {
            return true;
        }
        return false;
    },
    /**
     * 判断是否只包含字母或数字，并符合相应的长度
     * @param str 字符串
     * @param min 最小长度
     * @param max 最大长度
     * @returns {boolean}
     */
    isEnglishAndNumberAndLength: function (str, min, max) {
        var matchStr = /^[0-9a-zA-Z]+$/
        if (CheckDataUtil.isEnglish(str) && str.length >= min && str.length <= max) {
            return true;
        }
        if (CheckDataUtil.isNumber(str) && str.length >= min && str.length <= max) {
            return true;
        }
        if (matchStr.test(str) && str.length >= min && str.length <= max) {
            return true
        }
        return false;
    },
    /**
     * 验证是否为身份证
     * @param idCard  身份证号码
     * @returns {boolean} true是，false不是
     */
    isChinessIdCard: function (idCard) {
        var city = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江 ",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北 ",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏 ",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外 "
        };
        var tip = "";
        var pass = true;
        if (!idCard || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(idCard)) {
            tip = "身份证号格式错误";
            pass = false;
        }
        else if (!city[idCard.substr(0, 2)]) {
            tip = "地址编码错误";
            pass = false;
        }
        else {
            //18位身份证需要验证最后一位校验位
            if (idCard.length == 18) {
                idCard = idCard.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                //校验位
                var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++) {
                    ai = idCard[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if (parity[sum % 11] != idCard[17]) {
                    tip = "校验位错误";
                    pass = false;
                }
            }
        }
        if (!pass) {
            console.log(tip);
        }
        return pass;
    },
    okImage: function (file) {
        result = {flag: true, message: "图片符合要求"};
        fileName = file.type.split("/")[1]
        if (!/(jpg|jpeg|png|bmp|JPG|PNG)$/.test(fileName)) {
            result.flag = false;
            result.message = "图片类型必须是,jpeg,jpg,png,bmp中的一种";
        }
        if (file.size > 3000 * 1024) {
            result.flag = false;
            result.message = "图片文件大于3M,请重新选择";
        }
        return result
    },

    //判断图片格式
    checkFileImage: function (target) {
        var fileSize = 0;
        var filetypes = [".jpg", ".jpeg", ".png", ".JPG", ".JPEG", ".PNG"];
        var filepath = target.value;
        var filemaxsize = 1024 * 10;//10M
        if (filepath) {
            var isnext = false;
            var fileend = filepath.substring(filepath.lastIndexOf("."));
            if (filetypes && filetypes.length > 0) {
                for (var i = 0; i < filetypes.length; i++) {
                    if (filetypes[i] == fileend) {
                        isnext = true;
                        break;
                    }
                }
            }
            if (!isnext) {
                openTips("图片格式必须是jpeg,jpg,png中的一种！");
                target.value = "";
                return false;
            }
        } else {
            return false;
        }
        if (!target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            if (!fileSystem.FileExists(filePath)) {
                openTips("图片不存在，请重新输入！");
                return false;
            }
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
        }

        var size = fileSize / 1024;
        if (size > filemaxsize) {
            openTips("图片大小不能大于" + filemaxsize / 1024 + "M！");
            target.value = "";
            return false;
        }
        if (size <= 0) {
            openTips("图片大小不能为0M！");
            target.value = "";
            return false;
        }

        return true;
    }
}

/**
 * 输入框限制
 * @author sunfangwei
 * @type {{onlyAllowNum: QmLimitData.onlyAllowNum, onlyAllowEnglishAndNum: QmLimitData.onlyAllowEnglishAndNum, notAllowChinese: QmLimitData.notAllowChiness}}
 */
QmLimitData = {
    /**
     * 只允许输入数字
     * @param inputObj
     */
    onlyAllowNum: function (inputObj) {
        var matchStr = /[^\d]/g;
        var value = $(inputObj).val();
        if (matchStr.test(value)) {
            $(inputObj).get(0).value = $(inputObj).get(0).value.replace(matchStr, '');
        }
    },
    /**
     * 价格金额显示(只允许数字)
     * @param o:输入框的对象，
     * @param num：小数点后保留的位数
     */
    onlyAllowNum2: function (o, num) {
        var matchStr = /^-?\d+\.?\d{0,num}$/;
        if (!matchStr.test(o.value)) {
            if (isNaN(o.value)) {
                o.value = '';
            } else {
                var n = o.value.indexOf('.');
                var m = n + num + 1;
                if (n > -1 && o.value.length > m) {
                    o.value = o.value.substring(0, m);
                }
            }
        }
    },

    /**
     * 限制整数9位，小数12位
     * @param o:输入框的对象，
     * @param num:小数点后保留的位数
     */
    onlyAllowMoney: function (o, num) {
        var matchStr = /^([0-9]{1,9}|[0-9]{1,9}\.[0-9]{1,2})$/;
        if (!matchStr.test(o.value)) {
            if (isNaN(o.value)) {
                o.value = '';
            } else {
                var n = o.value.indexOf('.');
                var m = n + num + 1;
                if (n > -1 && o.value.length > m) {
                    o.value = o.value.substring(0, m);
                }
                if (n == -1 && o.value.length > 9){
                    o.value = o.value.substring(0,9);
                }
            }
        }
    },
    /**
     * 只请允许输入字母和数字
     * @param inputObj
     */
    onlyAllowEnglishAndNum: function (inputObj) {
        var matchStr = /[^A-Za-z0-9]/g;
        var value = $(inputObj).val();
        if (matchStr.test(value)) {
            $(inputObj).get(0).value = $(inputObj).get(0).value.replace(matchStr, '');
        }
    },

    /**
     * 只请允许输入字母和数字和中文
     * @param inputObj
     */
    onlyNumberAndEnglishAndChinese: function (inputObj) {
        var matchStr = /[^a-zA-Z0-9\_\u4e00-\u9fa5]/g;
        var value = $(inputObj).val();
        if (matchStr.test(value)) {
            $(inputObj).get(0).value = $(inputObj).get(0).value.replace(matchStr, '');
        }
    },

    /**
     * 不允许输入中文
     * @param inputObj
     */
    notAllowChinese: function (inputObj) {
        var matchStr = /[\u4E00-\u9FA5]/g;
        var value = $(inputObj).val();
        if (matchStr.test(value)) {
            $(inputObj).get(0).value = $(inputObj).get(0).value.replace(matchStr, '');
        }
    },

    /**
     * 不允许输入utf-16字符（防止表情）
     * @param inputObj
     */
    notExpression: function (inputObj) {
        var matchStr = /[\ud800-\udbff][\udc00-\udfff]/g;
        ;
        var value = $(inputObj).val();
        if (matchStr.test(value)) {
            $(inputObj).get(0).value = $(inputObj).get(0).value.replace(matchStr, '');
        }
    }
};

/**
 * 数据输入框数据类型(bate)
 * 使用说明：input框中加入属性[qmDataType],值为：EnglishAndNum，Num，Num2，NOtChiness，然后执行QmInputLimit.Init();
 * 属性说明：1、EnglishAndNum：只允许输入字母和数字
 *           2、Num：只允许输入整数
 *           3、Num2：只允许数字，并可以保留两位小数
 *           4、NOtChiness：不允许输入中文
 * @type {{Init: QmInputLimit.Init}}
 */
QmInputLimit = {
    /**
     * 初始化
     * @constructor
     */
    Init: function () {
        var inputList = $('input')
        for (i = 0; i < inputList.length; i++) {
            var inpuTyp = inputList.eq(i).attr("qmDataType");
            //只允许输入字母和数字
            if ("EnglishAndNum" == inpuTyp) {
                inputList.eq(i).on("keyup", function () {
                    QmLimitData.onlyAllowEnglishAndNum(this);
                });
                inputList.eq(i).on("change", function () {
                    QmLimitData.onlyAllowEnglishAndNum(this);
                });
                inputList.eq(i).on("blur", function () {
                    QmLimitData.onlyAllowEnglishAndNum(this);
                });
            }
            //只允许输入整数
            if ("Num" == inpuTyp) {
                inputList.eq(i).on("keyup", function () {
                    QmLimitData.onlyAllowNum(this);
                });
                inputList.eq(i).on("change", function () {
                    QmLimitData.onlyAllowNum(this);
                });
                inputList.eq(i).on("blur", function () {
                    QmLimitData.onlyAllowNum(this);
                });
            }

            //输入数字并可以保留两个小数
            if ("Num2" == inpuTyp) {
                inputList.eq(i).on("keyup", function () {
                    QmLimitData.onlyAllowNum2(this);
                });
                inputList.eq(i).on("change", function () {
                    QmLimitData.onlyAllowNum2(this);
                });
                inputList.eq(i).on("blur", function () {
                    QmLimitData.onlyAllowNum2(this);
                })
            }
            //不允许输入中文
            if ("NOtChiness" == inpuTyp) {
                inputList.eq(i).on("keyup", function () {
                    QmLimitData.notAllowChinese(this);
                });
                inputList.eq(i).on("change", function () {
                    QmLimitData.notAllowChinese(this);
                });
                inputList.eq(i).on("blur", function () {
                    QmLimitData.notAllowChinese(this);
                })
            }
        }
    }
};

function verificationPicFileTypeOfJpgPngJpeg(file) {
    var fileTypes = [".jpg", ".png",".jpeg"];
    var filePath = file.value==null||file.value==undefined?file.name:file.value;
    //当括号里面的值为0、空字符、false 、null 、undefined的时候就相当于false
    if(filePath){
        var isNext = false;
        // 获取文件后缀名
        var fileEnd = filePath.substring(filePath.lastIndexOf("."),filePath.length);
        // 循环判断后缀名是不是匹配 若 匹配则break
        for (var i = 0; i < fileTypes.length; i++) {
            if (fileTypes[i] == fileEnd || fileTypes[i].toUpperCase() == fileEnd) {
                isNext = true;
                break;
            }
        }
        // 返回匹配结果
        return isNext?true:false;
    }else {
        return false;
    }
}

