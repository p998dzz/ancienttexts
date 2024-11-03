package lt.ancienttexts.adapter.db;

import lt.ancienttexts.adapter.ImageAdapter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;

import java.util.Map;
import java.util.NoSuchElementException;

public class ImageDbAdapter implements ImageAdapter {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String TABLET_DETAILS_SQL = "SELECT * FROM Picture WHERE id_pk=:id";
    private final LobHandler lobHandler;

    public ImageDbAdapter(NamedParameterJdbcTemplate jdbcTemplate, LobHandler lobHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.lobHandler = lobHandler;
    }

    @Override
    public byte[] fetchTabletImage(Long id) throws NoSuchElementException {
        return jdbcTemplate.queryForObject(TABLET_DETAILS_SQL, Map.of("id", id), (rs, rowNum) -> {
            if (rs.next()) {
                return lobHandler.getBlobAsBytes(rs, "blob");
            } else {
                throw new NoSuchElementException(String.format("Image with id=%d was not found", id));
            }
        });
    }
}
