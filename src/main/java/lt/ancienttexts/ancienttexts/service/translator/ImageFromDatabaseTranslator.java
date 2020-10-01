package lt.ancienttexts.ancienttexts.service.translator;

import lt.ancienttexts.ancienttexts.service.entities.ListItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component("ImageFromDatabaseTranslator")
public class ImageFromDatabaseTranslator {

    @Value("${db.host}")
    private String dbHost;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;

    //private final String URL = "jdbc:postgresql://"+dbHost+"/ancient_texts_test";
    private final String TEXT_DETAILS_STATEMENT = "SELECT * FROM ancient_texts.\"PICTURES\" WHERE id_pk=?";
    private Properties props;

    public ImageFromDatabaseTranslator() throws Exception {
    }

    public byte[] fetchTabletImage(Long id) throws Exception {
        final String URL = "jdbc:postgresql://"+dbHost+"/ancient_texts_test";

        props = new Properties();
        props.setProperty("user", dbUser);
        props.setProperty("password",dbPassword);
        List<ListItemEntity> listOfEntries = new ArrayList<>();
        byte[] response = null;
        try (Connection connection = DriverManager.getConnection(URL, props)){
            PreparedStatement stmt = connection.prepareStatement(TEXT_DETAILS_STATEMENT);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LobHandler lobHandler = new DefaultLobHandler();
                response = lobHandler.getBlobAsBytes(rs, "picture");
            }
        }
        return response;
    }
}
