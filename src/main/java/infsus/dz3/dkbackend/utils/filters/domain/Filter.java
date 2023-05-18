package infsus.dz3.dkbackend.utils.filters.domain;

import infsus.dz3.dkbackend.utils.filters.enums.FilterType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class Filter<T> {

    FilterType filterType;
    String columnName;
    T value;
    List<T> values;
    T from;
    T to;

    public Filter(FilterType filterType, String columnName, T value, List<T> values, T from, T to) {
        if(filterType.equals(FilterType.IS_NULL) || filterType.equals(FilterType.IS_NOT_NULL)){
            this.filterType = filterType;
            this.columnName = columnName;
            this.value = null;
            this.values = null;
            this.from = null;
            this.to = null;
        }else{
            this.filterType = filterType;
            this.columnName = columnName;
            this.value = value;
            this.values = values;
            this.from = from;
            this.to = to;
        }
    }
}
