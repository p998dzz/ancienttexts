package lt.ancienttexts.adapter.db;

import lt.ancienttexts.adapter.ImageAdapter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.LobHandler;

import java.util.Map;
import java.util.NoSuchElementException;

public class ImageDbAdapter implements ImageAdapter {

    private static final String FETCH_IMAGE_SQL = "SELECT * FROM Image WHERE id=:id";
    private static final String CREATE_IMAGE_SQL = "INSERT INTO Image VALUES blob=:blob";
    private static final String UPDATE_IMAGE_SQL = "UPDATE Image SET blob=:blob WHERE id=:id";
    private static final String DELETE_IMAGE_SQL = "DELETE FROM Image WHERE id=:id";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final LobHandler lobHandler;

    public ImageDbAdapter(NamedParameterJdbcTemplate jdbcTemplate, LobHandler lobHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.lobHandler = lobHandler;
    }

    @Override
    public byte[] fetchTabletImage(Long id) throws NoSuchElementException {
        return jdbcTemplate.queryForObject(FETCH_IMAGE_SQL, Map.of("id", id), (rs, rowNum) -> {
            if (rs.next()) {
                return lobHandler.getBlobAsBytes(rs, "blob");
            } else {
                throw new NoSuchElementException(String.format("Image with id=%d was not found", id));
            }
        });
    }

    @Override
    public long createTabletImage(byte[] blob) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(CREATE_IMAGE_SQL, new MapSqlParameterSource().addValue("blob", blob), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateTabletImage(long id, byte[] newBlob) {
        jdbcTemplate.update(UPDATE_IMAGE_SQL, Map.of("blob", newBlob,"id", id));
    }

    @Override
    public void deleteTabletImage(long id) {
        jdbcTemplate.update(DELETE_IMAGE_SQL, Map.of("id", id));
    }
}
