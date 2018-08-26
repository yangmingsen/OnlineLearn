package utils;

public class NewIpHelper {

	public static String getNewIp(String oldIp, String newIp) {

        StringBuffer tmp1 = new StringBuffer(oldIp.substring(0,7));//先取出http://
        oldIp = oldIp.substring(7,oldIp.length());

        tmp1.append(newIp);
        tmp1.append(oldIp.substring(oldIp.indexOf(':'),oldIp.length()));

        return tmp1.toString();

    }
	
}
