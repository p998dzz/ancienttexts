package lt.ancienttexts.adapter.db;

import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class TabletsDbAdapter implements TabletAdapter {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final static String FETCH_TABLET_LIST_SQL = "SELECT id,title,location,interpreted,date_created FROM Tablet";
    private final static String SEARCH_TABLET_LIST_SQL = FETCH_TABLET_LIST_SQL.concat(" WHERE title LIKE '%:searchPhrase%'");
    private final static String FETCH_TABLET_DETAILS_SQL = "SELECT * FROM Tablet WHERE id=:id";
    private final static String UPDATE_TABLET_DETAILS_SQL = """
                UPDATE Tablet
                SET title = :title,
                    description = :description,
                    location = :location,
                    translation = :translation,
                    tablet_source = :tabletSource,
                    interpreted = :interpreted,
                    source_link = :sourceLink,
                    dated_at = :datedAt
                WHERE id = :id;
            """;
    private final static String INSERT_TABLET_DETAILS_SQL = """
                INSERT INTO Tablet (title, description, location, picture_id, translation,
                                    tablet_source, interpreted, date_added, source_link, dated_at)
                VALUES (:title, :description, :location, :pictureId, :translation,
                        :tabletSource, :interpreted, :dateAdded, :sourceLink, :datedAt);
            """;
    private final static String DELETE_TABLET_DETAILS_SQL = "DELETE FROM Tablet WHERE id=:id";

    public TabletsDbAdapter(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ListItemEntity> fetchAll() {
        return fetchEntries(null);
    }

    @Override
    public List<ListItemEntity> fetchEntries(String searchPhrase) {
        var sql = Strings.isBlank(searchPhrase) ? FETCH_TABLET_LIST_SQL : SEARCH_TABLET_LIST_SQL;
        return jdbcTemplate.query(sql, Map.of("searchPhrase", searchPhrase), (rs, rowNum) -> {
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
    public TabletEntity fetchDetails(Long id) {
        try {
            return jdbcTemplate.queryForObject(FETCH_TABLET_DETAILS_SQL, Map.of("id", id), new TabletEntityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No text details found for id: " + id, e);
        }
    }

    @Override
    public void createTablet(TabletDetails tabletDetails) {
        var params = new MapSqlParameterSource()
                .addValue("title", tabletDetails.getTitle())
                .addValue("description", tabletDetails.getDescription())
                .addValue("location", tabletDetails.getLocation())
                .addValue("pictureId", tabletDetails.getImageId())
                .addValue("translation", tabletDetails.getTranslation())
                .addValue("tabletSource", tabletDetails.getTabletSource())
                .addValue("interpreted", tabletDetails.getInterpreted())
                .addValue("sourceLink", tabletDetails.getSourceLink())
                .addValue("datedAt", tabletDetails.getDatedAt());

        jdbcTemplate.update(INSERT_TABLET_DETAILS_SQL, params);
    }

    @Override
    public void deleteTablet(Long id) {
        jdbcTemplate.update(DELETE_TABLET_DETAILS_SQL, Map.of("id", id));
    }

    @Override
    public void updateTablet(TabletDetails tabletDetails) {
        var params = new MapSqlParameterSource()
                .addValue("id", tabletDetails.getId())
                .addValue("title", tabletDetails.getTitle())
                .addValue("description", tabletDetails.getDescription())
                .addValue("location", tabletDetails.getLocation())
                .addValue("translation", tabletDetails.getTranslation())
                .addValue("tabletSource", tabletDetails.getTabletSource())
                .addValue("interpreted", tabletDetails.getInterpreted())
                .addValue("sourceLink", tabletDetails.getSourceLink())
                .addValue("datedAt", tabletDetails.getDatedAt());

        jdbcTemplate.update(UPDATE_TABLET_DETAILS_SQL, params);
    }

    private static class TabletEntityRowMapper implements RowMapper<TabletEntity> {
        @Override
        public TabletEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            var entry = new TabletEntity();
            entry.setId(rs.getLong(1));
            entry.setTitle(rs.getString(2));
            entry.setDescription(rs.getString(3));
            entry.setLocation(rs.getString(4));
            entry.setImageId(rs.getLong(5));
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