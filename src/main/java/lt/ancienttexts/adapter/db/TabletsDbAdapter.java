package lt.ancienttexts.adapter.db;

import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.service.entities.TextItemEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class TabletsDbAdapter implements TabletAdapter {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String TEXT_LIST_STATEMENT = "SELECT id_pk,title,location,interpreted,date_created FROM Tablet";
    private final String TEXT_DETAILS_STATEMENT = "SELECT * FROM Tablet WHERE id_pk=:id";

    public TabletsDbAdapter(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ListItemEntity> fetchAllEntries() {
        return jdbcTemplate.query(TEXT_LIST_STATEMENT, (rs, rowNum) -> {
            ListItemEntity entry = new ListItemEntity();
            entry.setId(rs.getLong(1));
            entry.setTitle(rs.getString(2));
            entry.setLocation(rs.getString(3));
            entry.setInterpreted(rs.getBoolean(4));
            entry.setDateAdded(rs.getDate(5));
            return entry;
        });
    }

    @Override
    public TextItemEntity fetchTabletDetails(Long id) {
        try {
            return jdbcTemplate.queryForObject(TEXT_DETAILS_STATEMENT, Map.of("id", id), new TextItemEntityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No text details found for id: " + id, e);
        }
    }

    private class TextItemEntityRowMapper implements RowMapper<TextItemEntity> {
        @Override
        public TextItemEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            TextItemEntity entry = new TextItemEntity();
            entry.setId(rs.getLong(1));
            entry.setTitle(rs.getString(2));
            entry.setDescription(rs.getString(3));
            entry.setLocation(rs.getString(4));
            entry.setOriginalPictureId(rs.getLong(5));
            entry.setTranslation(rs.getString(6));
            entry.setTabletSource(rs.getString(7));
            entry.setInterpreted(rs.getBoolean(8));
            entry.setDateAdded(rs.getDate(9));
            entry.setDatedAt(rs.getString(10));
            entry.setSourceLink(rs.getString(11));
            return entry;
        }
    }
}