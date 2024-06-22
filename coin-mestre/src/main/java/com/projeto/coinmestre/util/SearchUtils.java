package com.projeto.coinmestre.util;

import com.projeto.coinmestre.model.User;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

public class SearchUtils {

    private static final String TRANSLATE_FROM = "'áàâãäéèêëíìïóòôõöúùûüÁÀÂÃÄÉÈÊËÍÌÏÓÒÔÕÖÚÙÛÜçÇ'";
    private static final String TRANSLATE_TO = "'aaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcC'";

    private SearchUtils() {
    }

    public static Expression<String> removeAccents(CriteriaBuilder builder, Expression<String> expression) {
        return builder.function("translate", String.class, expression, builder.literal(TRANSLATE_FROM),
                builder.literal(TRANSLATE_TO));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> Specification<T> specByFilter(String filter, String... columns) {
        return filter == null || filter.trim().isEmpty() ? null : (root, query, builder) -> {
            Collection<Predicate> predicates = new ArrayList<>();
            for (String col : Arrays.asList(columns)) {
                Path path = root;
                String finalCol = col;
                String formatter = null;
                if (col.contains(".")) {
                    String[] split = col.split("[.]");
                    for (int i = 0; i < split.length - 1; i++) {
                        path = ((From<T, T>) path).join(split[i], JoinType.LEFT);
                    }
                    finalCol = split[split.length - 1];
                }
                if (finalCol.contains(":")) {
                    finalCol = finalCol.split(":")[0];
                    if (col.contains(":localDatetime"))
                        formatter = "DD/MM/YYYY HH24:MI";
                    else if (col.contains(":localDate"))
                        formatter = "DD/MM/YYYY";
                    else if (col.contains(":localTime"))
                        formatter = "HH24:MI";
                    else if (col.contains(":dayOfWeek"))
                        formatter = "dayOfWeek";
                }
                path = path.get(finalCol);

                if (formatter != null) {
                    if (formatter.equals("dayOfWeek")) {
                        List<Integer> positions = new ArrayList<>();
                        for (int j = 0; j < DayOfWeek.values().length; j++) {
                            if (DayOfWeek.values()[j].getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")).toUpperCase().contains(filter.toUpperCase())) {
                                positions.add(j);
                            }
                        }
                        if (!positions.isEmpty())
                            predicates.add(path.in(positions));
                    } else {
                        predicates.add(builder.like(
                                builder.function("TO_CHAR", String.class, path, builder.literal(formatter)),
                                builder.literal("%" + filter + "%")));
                    }
                } else {
                    predicates.add(builder.like(builder.upper(path.as(String.class)),
                            builder.upper(builder.literal("%" + filter + "%"))));
                }
            }
            return builder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static <T> Specification<T> specByDeleted(boolean deleted) {
        return (root, query, builder) -> builder.equal(root.get("deleted"), deleted);
    }


    public static <T> Specification<T> specByUser(User user) {
        return (root, query, builder) -> builder.equal(root.get("to"), user);
    }
}
