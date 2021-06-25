#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.log;

/**
 * Enum of supported operating systems.
 *
 */
public enum PlatformEnum {
    /**
     * Microsoft Windows
     */
    WINDOWS,
    /**
     * A flavor of Linux
     */
    LINUX,
    /**
     * macOS (OS X)
     */
    MACOSX,

    UNKNOWN
}