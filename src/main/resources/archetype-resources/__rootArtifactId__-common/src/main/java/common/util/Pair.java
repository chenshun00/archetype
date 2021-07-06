#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.util;

/**
 * 持有2个对象,不必要再去设置一个object了
 *
 * @author chenshun00@gmail.com
 * @since 2020/6/5 11:25 下午
 */
public class Pair<A, B> {

    public A first;
    public B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }


    public static <A, B> Pair<A, B> with(A first, B second) {
        return new Pair(first, second);
    }

}
