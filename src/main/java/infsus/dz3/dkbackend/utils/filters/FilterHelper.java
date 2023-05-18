package infsus.dz3.dkbackend.utils.filters;

import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

@Slf4j
public class FilterHelper {


    public static StringBuilder determineAndAppendWhereClause(StringBuilder query, List<Filter> filters, MapSqlParameterSource parameters, Map<String, Pair<String,String>> tableAndColumnByFilterColumnName){
        if(query == null || filters == null || parameters == null || tableAndColumnByFilterColumnName == null || filters.isEmpty()){
            //TODO baciti exception ili nesto smisliti
            return query;
        }
        query.append(" WHERE 1=1 ");

        for(Filter filter : filters){
            Pair tuple = tableAndColumnByFilterColumnName.get(filter.getColumnName());
            if(tuple == null){
                //TODO pokusava se filtrirati po koloni po kojoj nije definirano filtriranje, treba baciti exception ili ignorirati
                log.info("Pokusava se filtrirati po koloni po kojoj nije definirano filtriranje");
                continue;
            }
            switch (filter.getFilterType()){
                case LIKE:
                    query.append(" AND UPPER(").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(") LIKE :").append(filter.getColumnName());
                    parameters.addValue(filter.getColumnName(), "%"+ ((String)filter.getValue()).toUpperCase() + "%");
                    break;
                case EXACT:
                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" = :").append(filter.getColumnName());
                    parameters.addValue(filter.getColumnName(),filter.getValue());
                    break;
                case SELECT:
                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" IN (:").append(filter.getColumnName()).append(")");
                    parameters.addValue(filter.getColumnName(), filter.getValues());
                    break;
                case RANGE:
                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" BETWEEN :").append(filter.getColumnName()+"From").append(" AND :").append(filter.getColumnName() + "To");
                    parameters.addValue(filter.getColumnName()+"From", filter.getFrom());
                    parameters.addValue(filter.getColumnName()+"To",filter.getTo());
                    break;
                case BOOLEAN:
                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" = :").append(filter.getColumnName());
                    parameters.addValue(filter.getColumnName(), filter.getValue().equals(true) ? 1 : 0);
                    break;
                case IS_NULL:
                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" IS NULL ");
                    break;
                case IS_NOT_NULL:
                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" IS NOT NULL ");
                    break;
                    //TODO dodati i ostale tipove filtera
//                case GREATER_THAN:
//                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" = :").append(filter.getColumnName());
//                    break;
//                case GREATER_THAN_OR_EQUAL:
//                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" = :").append(filter.getColumnName());
//                    break;
//                case LESS_THAN:
//                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" = :").append(filter.getColumnName());
//                    break;
//                case LESS_THAN_OR_EQUAL:
//                    query.append(" AND ").append(tuple.getFirst()).append(".").append(tuple.getSecond()).append(" = :").append(filter.getColumnName());
//                    break;
            }

        }
        return query;
    }
}
