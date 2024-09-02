package Recorders.ggogit.domain.util.mybatis;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 자바 필드의 Boolean타입을 데이터베이스의 NUMBER(1)타입으로 변환하는 커스텀 핸들러입니다.
 * 이 커스텀 핸들러는 mybatis-config.xml에 매핑되어 있습니다.
 */

public class BooleanToNumberTypeHandler extends BaseTypeHandler<Boolean> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter ? 1 : 0);
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int ret = rs.getInt(columnName);
        return ret == 1;
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int ret = rs.getInt(columnIndex);
        return ret == 1;
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int ret = cs.getInt(columnIndex);
        return ret == 1;
    }
}
