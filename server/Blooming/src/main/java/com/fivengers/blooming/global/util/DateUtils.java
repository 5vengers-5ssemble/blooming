package com.fivengers.blooming.global.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {

    public static LocalDate findLastSunday() {
        // TODO-TEST : 일요일을 잘 반환 하는지 테스트 구현
        LocalDate todayDate = LocalDate.now();

        DayOfWeek todayDayOfWeek = todayDate.getDayOfWeek();

        return todayDate.minusDays(todayDayOfWeek.getValue());
    }

}
