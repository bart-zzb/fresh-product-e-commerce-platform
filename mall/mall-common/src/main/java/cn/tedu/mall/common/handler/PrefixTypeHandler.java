package cn.tedu.mall.common.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.beans.factory.annotation.Value;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 暂时不需要
 * 自动为img_url字段值添加file-path路径
 */
//@Component
public class PrefixTypeHandler implements TypeHandler<String> {
    @Value("${Base-url}")
    private String baseUrl;

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return addSuffix(rs.getString(columnName));
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return addSuffix(rs.getString(columnIndex));
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return addSuffix(cs.getString(columnIndex));
    }

    private String addSuffix(String originalUrl) {
        if (originalUrl != null) {
            return baseUrl + originalUrl;
        }
        return null;
    }
}