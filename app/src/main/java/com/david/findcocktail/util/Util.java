package com.david.findcocktail.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static final String TABLES_NAMES = "REL_COCKTAIL_INGREDIENT, INGREDIENT, COCKTAIL";

    public static final String QUERY_COCKTAIL = "SELECT * FROM COCKTAIL";

    public static final String QUERY_INGREDIENTS = " SELECT ING.NAME, RE.AMOUNT FROM REL_COCKTAIL_INGREDIENT RE " +
            " INNER JOIN INGREDIENT ING ON RE.ID_INGREDIENT = ING.ID_INGREDIENT" +
            " WHERE RE.ID_COCKTAIL = ";
}
