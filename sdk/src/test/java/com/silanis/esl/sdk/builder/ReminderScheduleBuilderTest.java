package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ReminderSchedule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReminderScheduleBuilderTest {

    @Test
    public void buildWithStringConstructor() {
        String packageId = "myPackageId";
        ReminderScheduleBuilder builder = ReminderScheduleBuilder.forPackageWithId( packageId );
        ReminderSchedule built = builder.build();
        assertThat( built.getPackageId().getId(), is( equalTo( packageId ) ) );
    }

    @Test
    public void buildWithPackageIdConstructor() {
        PackageId packageId = new PackageId( "myPackageId" );
        ReminderScheduleBuilder builder = ReminderScheduleBuilder.forPackageWithId( packageId );
        ReminderSchedule built = builder.build();
        assertThat( built.getPackageId().getId(), is( equalTo( packageId.getId() ) ) );
    }

    @Test
    public void buildWithDefaultValues() {
        ReminderScheduleBuilder builder = ReminderScheduleBuilder.forPackageWithId( "whoCares" );
        ReminderSchedule built = builder.build();
        assertThat( built.getDaysBetweenReminders(), is( equalTo( ReminderScheduleBuilder.DEFAULT_DAYS_BETWEEN_REMINDERS ) ) );
        assertThat( built.getDaysUntilFirstReminder(), is( equalTo( ReminderScheduleBuilder.DEFAULT_DAYS_UNTIL_FIRST_REMINDER ) ) );
        assertThat( built.getNumberOfRepetitions(), is( equalTo( ReminderScheduleBuilder.DEFAULT_NUMBER_OF_REPETITIONS ) ) );
    }

    @Test
    public void buildWithNonDefaultValues() {

        int daysBetweenReminders = 10;
        int daysUntilFirstReminder = 100;
        int numberOfRepetitions = 5;

        ReminderScheduleBuilder builder = ReminderScheduleBuilder.forPackageWithId( "whoCares" )
                .withDaysBetweenReminders( daysBetweenReminders )
                .withDaysUntilFirstReminder( daysUntilFirstReminder )
                .withNumberOfRepetitions( numberOfRepetitions );

        ReminderSchedule built = builder.build();
        assertThat( built.getDaysBetweenReminders(), is( equalTo( daysBetweenReminders ) ) );
        assertThat( built.getDaysUntilFirstReminder(), is( equalTo( daysUntilFirstReminder ) ) );
        assertThat( built.getNumberOfRepetitions(), is( equalTo( numberOfRepetitions ) ) );
    }
}
