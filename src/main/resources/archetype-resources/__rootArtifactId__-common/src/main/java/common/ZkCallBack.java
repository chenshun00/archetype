#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common;

import ${package}.secretzk.client.IZkCallBack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: ouzhouyou@raycloud.com
 * Date: 16/8/22
 * Time: 下午2:31
 * Version: 1.0
 */
public class ZkCallBack implements IZkCallBack {

    public static final Map<String, String> appSecretMap = new ConcurrentHashMap<String, String>();

    @Override
    public boolean changeSecret(Map<String/**appKey**/, String/**secret**/> secretMap) throws Exception {
        appSecretMap.putAll(secretMap);
        return false;
    }

    public static final String DEFAULT_APP_KEY = "12011554";

    public static String PARAMETER_API_SANDBOX_SERVICE_URL = "http://gw.api.taobao.com/router/rest";

}
