package com.ops.newsurvey;

/**
 * Created by harasar on 6/4/17.
 */

public class SurveyContract {

    private SurveyContract(){};

    public static class SurveyEntry {
        //table names
        public static final String TABLE_USER ="user_info";
        public static final String TABLE_QS ="question_info";
        public static final String TABLE_ACTIVITY ="activity_log";
        public static final String TABLE_OPT ="options_details";

        //keys of user table
        public static final String KEY_UID="Uid";
        public static final String KEY_USERNAME="username";
        public static final String KEY_NAME="name";
        public static final String KEY_EMAIL="email";
        public static final String KEY_GENDER="gender";
        public static final String KEY_PASSWORD="password";
        public static final String KEY_PIC_ID="pic_id";
        public static final String KEY_QUESTIONS="questions";

        //keys of QS table
        public static final String KEY_QID="Qid";
        public static final String KEY_QTEXT="qtext";
        public static final String KEY_RESPONSES="responses";
        public static final String KEY_CATEGORY="category";

        //keys of opt table
        public static final String KEY_OPTIONS="options";
        public static final String KEY_RESULTS="results";

    }
}
