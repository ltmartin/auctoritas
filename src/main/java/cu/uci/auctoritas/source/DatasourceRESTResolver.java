package cu.uci.auctoritas.source;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.source.mapper.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bichos on 4/05/16.
 */
public class DatasourceRESTResolver<T> implements DatasourceResolver<T> {

    @Override
    public List<T> getElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz) {

        List<T> result = new ArrayList<>();

        QueryExecution execution = new QueryEngineHTTP(endpoint, query);
        ResultSet qr = execution.execSelect();

        EntityMapper mapper = getRowMapper(clazz);

        while (qr.hasNext())
            result.add((T) mapper.mapRow(qr.next()));

        return result;
    }

    @Override
    public void postElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz) {
        //Recomendacion para la version 3
    }

    private EntityMapper<T> getRowMapper(Class<T> clazz){
        if(clazz == PersonalAuthor.class)
            return (EntityMapper<T>) new PersonalAuthorRowMapper();
        if(clazz == CorporateAuthor.class)
            return (EntityMapper<T>) new CorporativeAuthorRowMapper();
        if (clazz == Vocabulary.class)
            return (EntityMapper<T>) new VocabularyRowMapper();
        if (clazz == AuthorizedTerm.class)
            return (EntityMapper<T>) new AuthorizedTermRowMapper();
        return null;
    }
}
