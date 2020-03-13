package com.dist.liscence;

/**
 * Created by Administrator on 2019/1/7.
 * @author xupp
 *
 * @date 2019/1/7
 */
public class StringUtil {

    //获取常见的编码，主要是处理md txt 等文件乱码的情况
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";

    }

    public static String rtrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ( (st < len) && (chars[len-1] == c) ){
            len --;
        }
        return (len<chars.length)? str.substring(st, len): str;
    }


    public static void main(String[] args) {
        String str1="{\"regionType\":\"省\",\"data\":[{\"id\":104827,\"name\":\"安徽省\",\"key\":\"340000000000\",\"popular\":false,\"isSelected\":true},{\"id\":84874,\"name\":\"北京市\",\"key\":\"110000000000\",\"popular\":false,\"isSelected\":true},{\"id\":122004,\"name\":\"重庆市\",\"key\":\"500000000000\",\"popular\":false,\"isSelected\":true},{\"id\":106625,\"name\":\"福建省\",\"key\":\"350000000000\",\"popular\":false,\"isSelected\":true},{\"id\":127950,\"name\":\"贵州省\",\"key\":\"520000000000\",\"popular\":false,\"isSelected\":true},{\"id\":120313,\"name\":\"广西壮族自治区\",\"key\":\"450000000000\",\"popular\":false,\"isSelected\":true},{\"id\":118432,\"name\":\"广东省\",\"key\":\"440000000000\",\"popular\":true,\"isSelected\":true},{\"id\":133364,\"name\":\"甘肃省\",\"key\":\"620000000000\",\"popular\":false,\"isSelected\":true},{\"id\":114647,\"name\":\"湖北省\",\"key\":\"420000000000\",\"popular\":false,\"isSelected\":true},{\"id\":116263,\"name\":\"湖南省\",\"key\":\"430000000000\",\"popular\":false,\"isSelected\":true},{\"id\":121744,\"name\":\"海南省\",\"key\":\"460000000000\",\"popular\":false,\"isSelected\":true},{\"id\":111863,\"name\":\"河南省\",\"key\":\"410000000000\",\"popular\":false,\"isSelected\":true},{\"id\":85551,\"name\":\"河北省\",\"key\":\"130000000000\",\"popular\":false,\"isSelected\":true},{\"id\":93986,\"name\":\"黑龙江省\",\"key\":\"230000000000\",\"popular\":false,\"isSelected\":true},{\"id\":101612,\"name\":\"江苏省\",\"key\":\"320000000000\",\"popular\":false,\"isSelected\":true},{\"id\":107914,\"name\":\"江西省\",\"key\":\"360000000000\",\"popular\":false,\"isSelected\":true},{\"id\":92861,\"name\":\"吉林省\",\"key\":\"220000000000\",\"popular\":false,\"isSelected\":true},{\"id\":91142,\"name\":\"辽宁省\",\"key\":\"210000000000\",\"popular\":false,\"isSelected\":true},{\"id\":89743,\"name\":\"内蒙古自治区\",\"key\":\"150000000000\",\"popular\":false,\"isSelected\":true},{\"id\":135400,\"name\":\"宁夏回族自治区\",\"key\":\"640000000000\",\"popular\":false,\"isSelected\":true},{\"id\":134914,\"name\":\"青海省\",\"key\":\"630000000000\",\"popular\":false,\"isSelected\":true},{\"id\":101360,\"name\":\"上海市\",\"key\":\"310000000000\",\"popular\":false,\"isSelected\":true},{\"id\":123077,\"name\":\"四川省\",\"key\":\"510000000000\",\"popular\":false,\"isSelected\":true},{\"id\":109820,\"name\":\"山东省\",\"key\":\"370000000000\",\"popular\":false,\"isSelected\":true},{\"id\":88113,\"name\":\"山西省\",\"key\":\"140000000000\",\"popular\":false,\"isSelected\":true},{\"id\":131917,\"name\":\"陕西省\",\"key\":\"610000000000\",\"popular\":true,\"isSelected\":true},{\"id\":85228,\"name\":\"天津市\",\"key\":\"120000000000\",\"popular\":false,\"isSelected\":true},{\"id\":131128,\"name\":\"西藏自治区\",\"key\":\"540000000000\",\"popular\":true,\"isSelected\":true},{\"id\":135698,\"name\":\"新疆维吾尔自治区\",\"key\":\"650000000000\",\"popular\":false,\"isSelected\":true},{\"id\":129534,\"name\":\"云南省\",\"key\":\"530000000000\",\"popular\":false,\"isSelected\":true},{\"id\":103314,\"name\":\"浙江省\",\"key\":\"330000000000\",\"popular\":false,\"isSelected\":true}]}";
        String str2="{\"regionType\":\"省\",\"data\":[{\"id\":104827,\"name\":\"安徽\",\"key\":\"340000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"340000000000\"},{\"id\":371302,\"name\":\"澳门\",\"key\":\"820000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"820000000000\"},{\"id\":84874,\"name\":\"北京市\",\"key\":\"110000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"110000000000\"},{\"id\":122004,\"name\":\"重庆市\",\"key\":\"500000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"500000000000\"},{\"id\":106625,\"name\":\"福建\",\"key\":\"350000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"350000000000\"},{\"id\":127950,\"name\":\"贵州\",\"key\":\"520000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"520000000000\"},{\"id\":118432,\"name\":\"广东\",\"key\":\"440000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"440000000000\"},{\"id\":120313,\"name\":\"广西\",\"key\":\"450000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"450000000000\"},{\"id\":133364,\"name\":\"甘肃\",\"key\":\"620000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"620000000000\"},{\"id\":114647,\"name\":\"湖北\",\"key\":\"420000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"420000000000\"},{\"id\":116263,\"name\":\"湖南\",\"key\":\"430000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"430000000000\"},{\"id\":121744,\"name\":\"海南\",\"key\":\"460000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"460000000000\"},{\"id\":111863,\"name\":\"河南\",\"key\":\"410000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"410000000000\"},{\"id\":85551,\"name\":\"河北\",\"key\":\"130000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"130000000000\"},{\"id\":93986,\"name\":\"黑龙江\",\"key\":\"230000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"230000000000\"},{\"id\":101612,\"name\":\"江苏\",\"key\":\"320000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"320000000000\"},{\"id\":107914,\"name\":\"江西\",\"key\":\"360000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"360000000000\"},{\"id\":92861,\"name\":\"吉林\",\"key\":\"220000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"220000000000\"},{\"id\":91142,\"name\":\"辽宁\",\"key\":\"210000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"210000000000\"},{\"id\":89743,\"name\":\"内蒙古\",\"key\":\"150000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"150000000000\"},{\"id\":135400,\"name\":\"宁夏\",\"key\":\"640000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"640000000000\"},{\"id\":134914,\"name\":\"青海\",\"key\":\"630000000000\",\"popular\":true,\"isSelected\":true,\"code\":\"630000000000\"},{\"id\":123077,\"name\":\"四川\",\"key\":\"510000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"510000000000\"},{\"id\":101360,\"name\":\"上海市\",\"key\":\"310000000000\",\"popular\":true,\"isSelected\":true,\"code\":\"310000000000\"},{\"id\":109820,\"name\":\"山东\",\"key\":\"370000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"370000000000\"},{\"id\":88113,\"name\":\"山西\",\"key\":\"140000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"140000000000\"},{\"id\":131917,\"name\":\"陕西\",\"key\":\"610000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"610000000000\"},{\"id\":85228,\"name\":\"天津市\",\"key\":\"120000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"120000000000\"},{\"id\":371300,\"name\":\"台湾\",\"key\":\"710000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"710000000000\"},{\"id\":131128,\"name\":\"西藏\",\"key\":\"540000000000\",\"popular\":true,\"isSelected\":true,\"code\":\"540000000000\"},{\"id\":135698,\"name\":\"新疆\",\"key\":\"650000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"650000000000\"},{\"id\":371301,\"name\":\"香港\",\"key\":\"810000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"810000000000\"},{\"id\":129534,\"name\":\"云南\",\"key\":\"530000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"530000000000\"},{\"id\":103314,\"name\":\"浙江\",\"key\":\"330000000000\",\"popular\":false,\"isSelected\":true,\"code\":\"330000000000\"}]}";
        String str3="{\"regionType\":\"省\",\"data\":[{\"id\":104827,\"name\":\"安徽省\",\"key\":\"340000000000\",\"popular\":false,\"isSelected\":true},{\"id\":84874,\"name\":\"北京市\",\"key\":\"110000000000\",\"popular\":false,\"isSelected\":true},{\"id\":122004,\"name\":\"重庆市\",\"key\":\"500000000000\",\"popular\":false,\"isSelected\":true},{\"id\":106625,\"name\":\"福建省\",\"key\":\"350000000000\",\"popular\":false,\"isSelected\":true},{\"id\":127950,\"name\":\"贵州省\",\"key\":\"520000000000\",\"popular\":false,\"isSelected\":true},{\"id\":118432,\"name\":\"广东省\",\"key\":\"440000000000\",\"popular\":false,\"isSelected\":true},{\"id\":120313,\"name\":\"广西壮族自治区\",\"key\":\"450000000000\",\"popular\":false,\"isSelected\":true},{\"id\":133364,\"name\":\"甘肃省\",\"key\":\"620000000000\",\"popular\":false,\"isSelected\":true},{\"id\":114647,\"name\":\"湖北省\",\"key\":\"420000000000\",\"popular\":false,\"isSelected\":true},{\"id\":116263,\"name\":\"湖南省\",\"key\":\"430000000000\",\"popular\":false,\"isSelected\":true},{\"id\":121744,\"name\":\"海南省\",\"key\":\"460000000000\",\"popular\":false,\"isSelected\":true},{\"id\":111863,\"name\":\"河南省\",\"key\":\"410000000000\",\"popular\":false,\"isSelected\":true},{\"id\":85551,\"name\":\"河北省\",\"key\":\"130000000000\",\"popular\":false,\"isSelected\":true},{\"id\":93986,\"name\":\"黑龙江省\",\"key\":\"230000000000\",\"popular\":false,\"isSelected\":true},{\"id\":101612,\"name\":\"江苏省\",\"key\":\"320000000000\",\"popular\":false,\"isSelected\":true},{\"id\":107914,\"name\":\"江西省\",\"key\":\"360000000000\",\"popular\":false,\"isSelected\":true},{\"id\":92861,\"name\":\"吉林省\",\"key\":\"220000000000\",\"popular\":false,\"isSelected\":true},{\"id\":91142,\"name\":\"辽宁省\",\"key\":\"210000000000\",\"popular\":false,\"isSelected\":true},{\"id\":89743,\"name\":\"内蒙古自治区\",\"key\":\"150000000000\",\"popular\":false,\"isSelected\":true},{\"id\":135400,\"name\":\"宁夏回族自治区\",\"key\":\"640000000000\",\"popular\":false,\"isSelected\":true},{\"id\":134914,\"name\":\"青海省\",\"key\":\"630000000000\",\"popular\":true,\"isSelected\":true},{\"id\":123077,\"name\":\"四川省\",\"key\":\"510000000000\",\"popular\":false,\"isSelected\":true},{\"id\":101360,\"name\":\"上海市\",\"key\":\"310000000000\",\"popular\":true,\"isSelected\":true},{\"id\":109820,\"name\":\"山东省\",\"key\":\"370000000000\",\"popular\":false,\"isSelected\":true},{\"id\":88113,\"name\":\"山西省\",\"key\":\"140000000000\",\"popular\":false,\"isSelected\":true},{\"id\":131917,\"name\":\"陕西省\",\"key\":\"610000000000\",\"popular\":false,\"isSelected\":true},{\"id\":85228,\"name\":\"天津市\",\"key\":\"120000000000\",\"popular\":false,\"isSelected\":true},{\"id\":131128,\"name\":\"西藏自治区\",\"key\":\"540000000000\",\"popular\":true,\"isSelected\":true},{\"id\":135698,\"name\":\"新疆维吾尔自治区\",\"key\":\"650000000000\",\"popular\":false,\"isSelected\":true},{\"id\":129534,\"name\":\"云南省\",\"key\":\"530000000000\",\"popular\":false,\"isSelected\":true},{\"id\":103314,\"name\":\"浙江省\",\"key\":\"330000000000\",\"popular\":false,\"isSelected\":true}]}";
        System.out.println("参数长度>>" + str1.length());
        System.out.println("参数长度>>" + str2.length());
        String a = "123abc";
        String b = "中文";
        System.out.println("参数长度>>" + a.length());
        System.out.println("参数长度>>" + b.length());

    }
}