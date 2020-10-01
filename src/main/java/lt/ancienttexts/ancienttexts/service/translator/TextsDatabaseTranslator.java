package lt.ancienttexts.ancienttexts.service.translator;

import lt.ancienttexts.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.ancienttexts.service.entities.TextItemEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component("TextsDatabaseTranslator")
public class TextsDatabaseTranslator {

    @Value("${db.host}")
    private String dbHost;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;

    private final String TEXT_LIST_STATEMENT = "SELECT id_pk,title,location,interpreted,date_created FROM ancient_texts.\"TEXT_ENTRIES\"";
    private final String TEXT_DETAILS_STATEMENT = "SELECT * FROM ancient_texts.\"TEXT_ENTRIES\" WHERE id_pk=?";
    private Properties props;

    public TextsDatabaseTranslator() throws Exception {
    }

    public List<ListItemEntity> fetchAllEntries() throws Exception {
        final String URL = "jdbc:postgresql://"+dbHost+"/ancient_texts_test";
        props = new Properties();
        props.setProperty("user",dbUser);
        props.setProperty("password",dbPassword);
        List<ListItemEntity> listOfEntries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, props)){
            PreparedStatement stmt = connection.prepareStatement(TEXT_LIST_STATEMENT);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ListItemEntity entry = new ListItemEntity();
                entry.setId(rs.getLong(1));
                entry.setTitle(rs.getString(2));
                entry.setLocation(rs.getString(3));
                entry.setInterpreted(rs.getBoolean(4));
                entry.setDateAdded(rs.getDate(5));
                listOfEntries.add(entry);
            }
        }
        return listOfEntries;
    }

    public TextItemEntity fetchTextDetails(Long id) throws Exception {
        final String URL = "jdbc:postgresql://"+dbHost+"/ancient_texts_test";

        TextItemEntity entry = new TextItemEntity();
        try (Connection connection = DriverManager.getConnection(URL, props);){
            PreparedStatement stmt = connection.prepareStatement(TEXT_DETAILS_STATEMENT);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                entry.setId(rs.getLong(1));
                entry.setTitle(rs.getString(2));
                entry.setLocation(rs.getString(4));
                entry.setInterpreted(rs.getBoolean(8));
                entry.setDateAdded(rs.getDate(9));
                entry.setDescription(rs.getString(3));
                entry.setOriginalPictureId(rs.getLong(5));
                entry.setTranslation(rs.getString(6));
                entry.setTabletSource(rs.getString(7));
                break;
            }
        }
        return entry;
    }
}