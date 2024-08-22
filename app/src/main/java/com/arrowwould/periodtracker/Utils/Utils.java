package com.arrowwould.periodtracker.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.HtmlCompat;


import com.arrowwould.periodtracker.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class Utils {
    public static void setStatusBarColor(int i, Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(ContextCompat.getColor(activity, i));
    }

    public static String getStringFromObj(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    public static boolean getBoolFromObj(Object obj) {
        return Boolean.TRUE.equals(obj != null ? (Boolean) obj : null);
    }

    public static void makeTransparentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(1280);
        window.setStatusBarColor(0);
    }

    public static void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(512, 512);
    }

    public static List<HashMap<String, Object>> readAssetFile(Context context, String str) {
        String str2;
        try {
            return JsonHelper.getJsonData(context, str);
        } catch (Exception unused) {
            if (str.endsWith("_c.json")) {
                str2 = "en_c.json";
            } else {
                str2 = str.endsWith("_g.json") ? "en_g.json" : "en.json";
            }
            try {
                return JsonHelper.getJsonData(context, str2);
            } catch (IOException unused2) {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }

    public static int getResId(String str, Activity activity) {
        String str2 = str;
        int identifier = activity.getResources().getIdentifier(str2, "drawable", activity.getPackageName());
        if (identifier == 0) {
            str2.replace("ı", "i");
            identifier = activity.getResources().getIdentifier(str2, "drawable", activity.getPackageName());
        }
        Log.e("MYTAG", "ErrorNo: getResId drawable:" + str2 + " : " + identifier);
        return identifier;
    }

    public static String htmlToText(String str) {
        return HtmlCompat.fromHtml(str, 0).toString();
    }

    public static String lowerUnder(String str) {
        if (str.endsWith("?")) {
            str = str.substring(0, str.length() - 1);
        }
        String replace = str.toLowerCase().replace(" ", "_").replace("&", "and").replace("-", "_").replace(",", "").replace("'", "");
        if (startsWithDigit(replace)) {
            return "_" + replace;
        }
        return replace;
    }

    private static boolean startsWithDigit(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return Character.isDigit(str.charAt(0));
    }

    public static void setButtonTint(AppCompatButton appCompatButton, int i) {
        Drawable wrap = DrawableCompat.wrap(appCompatButton.getBackground());
        DrawableCompat.setTint(wrap, ContextCompat.getColor(appCompatButton.getContext(), i));
        appCompatButton.setBackgroundDrawable(wrap);
    }

    public static void setTint(LinearLayout linearLayout, int i) {
        linearLayout.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(linearLayout.getContext(), i)));
    }

    public static void setButtonTint(Button button, int i) {
        button.setBackgroundTintList(ContextCompat.getColorStateList(button.getContext(), i));
    }

    public static void darkStatusBarIcons(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            activity.getWindow().getDecorView().setSystemUiVisibility(8192);
        }
    }

    public static void hideKeyboard(Activity activity) {
        try {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int setImage(String s) {
        switch (s) {
            case "how_do_i_know_when_i_am_ovulating":
                return R.drawable.how_do_i_know_when_i_am_ovulating;
            case "what_are_the_signs_of_ovulation":
                return R.drawable.what_are_the_signs_of_ovulation;
            case "how_long_does_ovulation_last":
                return R.drawable.how_long_does_ovulation_last;

            case "what_is_the_best_way_to_track_ovulation":
                return R.drawable.what_is_the_best_way_to_track_ovulation;

            case "can_i_still_ovulate_if_i_have_irregular_periods":
                return R.drawable.can_i_still_ovulate_if_i_have_irregular_periods;

            case "what_is_the_most_fertile_time_during_ovulation":
                return R.drawable.what_is_the_most_fertile_time_during_ovulation;

            case "can_i_get_pregnant_if_i_have_sex_before_ovulation":
                return R.drawable.can_i_get_pregnant_if_i_have_sex_before_ovulation;

            case "how_can_i_increase_my_chances_of_ovulation":
                return R.drawable.how_can_i_increase_my_chances_of_ovulation;

            case "what_are_the_symptoms_of_ovulation":
                return R.drawable.what_are_the_symptoms_of_ovulation;

            case "can_i_ovulate_without_having_a_period":
                return R.drawable.can_i_ovulate_without_having_a_period;

            case "can_stress_affect_ovulation":
                return R.drawable.can_stress_affect_ovulation;

            case "how_does_age_affect_ovulation":
                return R.drawable.how_does_age_affect_ovulation;

            case "can_i_ovulate_twice_in_one_cycle":
                return R.drawable.can_i_ovulate_twice_in_one_cycle;

            case "can_i_get_pregnant_during_early_ovulation":
                return R.drawable.can_i_get_pregnant_during_early_ovulation;

            case "can_i_still_ovulate_with_low_progesterone":
                return R.drawable.can_i_still_ovulate_with_low_progesterone;

            case "can_i_ovulate_while_breastfeeding":
                return R.drawable.can_i_ovulate_while_breastfeeding;

            case "can_i_get_pregnant_during_late_ovulation":
                return R.drawable.can_i_get_pregnant_during_late_ovulation;

            case "can_i_ovulate_without_cervical_mucus":
                return R.drawable.can_i_ovulate_without_cervical_mucus;

            case "what_are_the_causes_of_ovulation_pain":
                return R.drawable.what_are_the_causes_of_ovulation_pain;

            case "how_does_birth_control_affect_ovulation":
                return R.drawable.how_does_birth_control_affect_ovulation;

            case "can_i_get_pregnant_during_ovulation":
                return R.drawable.can_i_get_pregnant_during_ovulation;

            case "how_to_improve_fertility":
                return R.drawable.how_to_improve_fertility;

            case "what_are_the_signs_of_fertility_in_females":
                return R.drawable.what_are_the_signs_of_fertility_in_females;

            case "how_to_test_fertility_in_females":
                return R.drawable.how_to_test_fertility_in_females;

            case "what_is_the_normal_fertility_rate":
                return R.drawable.what_is_the_normal_fertility_rate;

            case "what_are_the_causes_of_infertility_in_females":
                return R.drawable.what_are_the_causes_of_infertility_in_females;

            case "can_stress_affect_fertility":
                return R.drawable.can_stress_affect_fertility;

            case "can_diet_affect_fertility":
                return R.drawable.can_diet_affect_fertility;

            case "can_lifestyle_affect_fertility":
                return R.drawable.can_lifestyle_affect_fertility;

            case "how_to_boost_female_fertility_naturally":
                return R.drawable.how_to_boost_female_fertility_naturally;

            case "how_to_track_ovulation_for_better_fertility":
                return R.drawable.how_to_track_ovulation_for_better_fertility;

            case "how_to_improve_fertility_after_age_35":
                return R.drawable.how_to_improve_fertility_after_age_35;

            case "can_i_get_pregnant_with_one_fallopian_tube":
                return R.drawable.can_i_get_pregnant_with_one_fallopian_tube;

            case "how_to_improve_fertility_with_low_sperm_count":
                return R.drawable.how_to_improve_fertility_with_low_sperm_count;

            case "how_to_improve_fertility_with_irregular_periods":
                return R.drawable.how_to_improve_fertility_with_irregular_periods;

            case "how_to_improve_fertility_with_thyroid_problems":
                return R.drawable.how_to_improve_fertility_with_thyroid_problems;

            case "can_i_get_pregnant_with_irregular_periods":
                return R.drawable.can_i_get_pregnant_with_irregular_periods;

            case "can_i_get_pregnant_with_low_sperm_motility":
                return R.drawable.can_i_get_pregnant_with_low_sperm_motility;

            case "what_is_a_normal_period":
                return R.drawable.what_is_a_normal_period;

            case "what_is_a_heavy_period":
                return R.drawable.what_is_a_heavy_period;

            case "what_is_a_light_period":
                return R.drawable.what_is_a_light_period;

            case "what_are_the_signs_of_an_irregular_period":
                return R.drawable.what_are_the_signs_of_an_irregular_period;

            case "how_can_i_make_my_period_come_faster":
                return R.drawable.how_can_i_make_my_period_come_faster;

            case "how_can_i_delay_my_period":
                return R.drawable.how_can_i_delay_my_period;

            case "how_can_i_relieve_pms_symptoms":
                return R.drawable.how_can_i_relieve_pms_symptoms;

            case "what_are_the_symptoms_of_pms":
                return R.drawable.what_are_the_symptoms_of_pms;

            case "how_can_i_manage_period_cramps":
                return R.drawable.how_can_i_manage_period_cramps;

            case "can_exercise_affect_my_period":
                return R.drawable.can_exercise_affect_my_period;

            case "can_diet_affect_my_period":
                return R.drawable.can_diet_affect_my_period;

            case "can_stress_affect_my_period":
                return R.drawable.can_stress_affect_my_period;

            case "how_can_i_manage_heavy_bleeding_during_my_period":
                return R.drawable.how_can_i_manage_heavy_bleeding_during_my_period;

            case "how_can_i_track_my_period":
                return R.drawable.how_can_i_track_my_period;

            case "how_can_i_calculate_my_period_cycle":
                return R.drawable.how_can_i_calculate_my_period_cycle;

            case "how_can_i_use_birth_control_to_regulate_my_period":
                return R.drawable.how_can_i_use_birth_control_to_regulate_my_period;

            case "how_long_is_a_menstrual_cycle":
                return R.drawable.how_long_is_a_menstrual_cycle;

            case "what_is_a_normal_menstrual_cycle":
                return R.drawable.what_is_a_normal_menstrual_cycle;

            case "what_is_an_irregular_menstrual_cycle":
                return R.drawable.what_is_an_irregular_menstrual_cycle;

            case "how_can_i_track_my_ovulation":
                return R.drawable.how_can_i_track_my_ovulation;

            case "can_i_get_pregnant_during_my_menstrual_cycle":
                return R.drawable.can_i_get_pregnant_during_my_menstrual_cycle;

            case "can_i_get_pregnancan_stress_affect_my_menstrual_cyclet_during_my_menstrual_cycle":
                return R.drawable.can_stress_affect_my_menstrual_cycle;

            case "can_diet_affect_my_menstrual_cycle":
                return R.drawable.can_diet_affect_my_menstrual_cycle;

            case "can_exercise_affect_my_menstrual_cycle":
                return R.drawable.can_exercise_affect_my_menstrual_cycle;

            case "how_can_i_manage_menstrual_cramps":
                return R.drawable.how_can_i_manage_menstrual_cramps;

            case "how_can_i_manage_heavy_bleeding_during_my_menstrual_cycle":
                return R.drawable.how_can_i_manage_heavy_bleeding_during_my_menstrual_cycle;

            case "what_is_ovulation_and_how_does_it_affect_fertility":
                return R.drawable.what_is_ovulation_and_how_does_it_affect_fertility;

            case "how_long_is_a_typical_menstrual_cycle":
                return R.drawable.can_i_get_pregnant_during_my_menstrual_cycle;

            case "what_is_the_menstrual_phase_of_the_menstrual_cycle":
                return R.drawable.what_is_the_menstrual_phase_of_the_menstrual_cycle;

            case "what_is_the_fertile_window":
                return R.drawable.what_is_the_fertile_window;

            case "what_are_the_best_ways_to_increase_fertility":
                return R.drawable.what_are_the_best_ways_to_increase_fertility;

            case "how_does_age_affect_fertility":
                return R.drawable.how_does_age_affect_fertility;

            case "can_medications_affect_fertility":
                return R.drawable.can_medications_affect_fertility;

            case "what_are_the_best_natural_ways_to_improve_fertility":
                return R.drawable.what_are_the_best_natural_ways_to_improve_fertility;

            case "how_can_i_increase_my_chances_of_getting_pregnant":
                return R.drawable.how_can_i_increase_my_chances_of_getting_pregnant;

            case "can_i_get_pregnant_if_i_have_sex_on_my_period":
                return R.drawable.can_i_get_pregnant_if_i_have_sex_on_my_period;

            case "what_is_secondary_infertility":
                return R.drawable.what_is_secondary_infertility;

            case "what_is_unexplained_infertility":
                return R.drawable.what_is_unexplained_infertility;

            case "how_can_i_manage_pms_symptoms":
                return R.drawable.how_can_i_manage_pms_symptoms;

            case "what_is_infertility_and_what_are_the_causes":
                return R.drawable.what_is_infertility_and_what_are_the_causes;

            case "what_are_the_treatment_options_for_infertility":
                return R.drawable.what_are_the_treatment_options_for_infertility;

            case "what_are_the_side_effects_of_fertility_medications":
                return R.drawable.what_are_the_side_effects_of_fertility_medications;

            case "how_can_i_prepare_for_a_healthy_pregnancy":
                return R.drawable.how_can_i_prepare_for_a_healthy_pregnancy;

            case "what_foods_reduce_period_pain":
                return R.drawable.what_foods_reduce_period_pain;

            case "what_makes_period_cramps_worse":
                return R.drawable.what_makes_period_cramps_worse;

            case "what_drink_is_good_for_cramps":
                return R.drawable.what_drink_is_good_for_cramps;

            case "what_is_the_main_cause_of_cramp":
                return R.drawable.what_is_the_main_cause_of_cramp;

            case "why_do_my_legs_hurt_on_my_period":
                return R.drawable.why_do_my_legs_hurt_on_my_period;

            case "what_foods_cause_heavy_periods":
                return R.drawable.what_foods_cause_heavy_periods;

            case "what_is_emergency_contraception":
                return R.drawable.what_is_emergency_contraception;

            case "what_can_i_do_to_maintain_my_reproductive_health":
                return R.drawable.what_can_i_do_to_maintain_my_reproductive_health;

            case "how_can_i_improve_my_female_reproductive_system":
                return R.drawable.how_can_i_improve_my_female_reproductive_system;

            case "how_many_days_after_my_period_can_i_get_pregnant_calculator":
                return R.drawable.how_many_days_after_my_period_can_i_get_pregnant_calculator;

            case "how_effective_are_natural_family_planning_methods":
                return R.drawable.how_effective_are_natural_family_planning_methods;

            case "can_i_get_pregnant_right_after_my_period_ends":
                return R.drawable.can_i_get_pregnant_right_after_my_period_ends;

            case "is_it_safe_to_take_medicine_for_improve_fertility":
                return R.drawable.is_it_safe_to_take_medicine_for_improve_fertility;

            case "is_it_good_to_take_medicine_to_conceive":
                return R.drawable.is_it_good_to_take_medicine_to_conceive;

            case "what_are_the_side_effects_of_fertility_pills":
                return R.drawable.what_are_the_side_effects_of_fertility_pills;

            case "what_are_the_symptoms_of_poor_egg_quality":
                return R.drawable.what_are_the_symptoms_of_poor_egg_quality;

            case "how_can_i_increase_my_ovaries_eggs_naturally":
                return R.drawable.how_can_i_increase_my_ovaries_eggs_naturally;

            case "increase_male_fertility":
                return R.drawable.increase_male_fertility;

            case "transition_to_fertility":
                return R.drawable.transition_to_fertility;

            case "female_fertility_check":
                return R.drawable.female_fertility_check;

            case "_3_causes_of_infertility":
                return R.drawable._3_causes_of_infertility;

            case "foods_for_sperm_count":
                return R.drawable.foods_for_sperm_count;

            case "male_infertility_cause":
                return R.drawable.male_infertility_cause;

            case "pregnancy_in_infertile_women":
                return R.drawable.pregnancy_in_infertile_women;

            case "post_sex_precautions":
                return R.drawable.post_sex_precautions;

            case "best_days_for_sex":
                return R.drawable.best_days_for_sex;

            case "frequency_of_sex":
                return R.drawable.frequency_of_sex;

            case "sex_protection":
                return R.drawable.sex_protection;

            case "lifelong_intimacy":
                return R.drawable.lifelong_intimacy;

            case "intimacy_for_men":
                return R.drawable.intimacy_for_men;

            case "power_of_eye_contact":
                return R.drawable.power_of_eye_contact;

            case "stress_and_delayed_period":
                return R.drawable.stress_and_delayed_period;

            case "cramps_relief":
                return R.drawable.cramps_relief;

            case "stress_period_overview":
                return R.drawable.stress_period_overview;

            case "stress_and_fertility_connection":
                return R.drawable.stress_and_fertility_connection;

            case "stress_and_fertility":
                return R.drawable.stress_and_fertility;

            case "emotional_impact_on_ovulation":
                return R.drawable.emotional_impact_on_ovulation;

            case "stress_and_ovulation_delay":
                return R.drawable.stress_and_ovulation_delay;

            case "managing_stressful_periods":
                return R.drawable.managing_stressful_periods;

            case "emotions_and_ovulation":
                return R.drawable.emotions_and_ovulation;

            case "coping_with_stressful_periods":
                return R.drawable.coping_with_stressful_periods;

            case "cramps_without_period":
                return R.drawable.cramps_without_period;

            case "damaging_egg_quality":
                return R.drawable.damaging_egg_quality;

            case "relaxation_for_pregnancy":
                return R.drawable.relaxation_for_pregnancy;

            case "igniting_passion":
                return R.drawable.igniting_passion;

            case "secrets_to_lasting_closeness":
                return R.drawable.secrets_to_lasting_closeness;

            case "intimacy_hacks":
                return R.drawable.intimacy_hacks;

            case "role_of_touch":
                return R.drawable.role_of_touch;

            case "types_of_intimacy":
                return R.drawable.types_of_intimacy;

            case "art_of_intimacy":
                return R.drawable.art_of_intimacy;

            case "sparking_desire":
                return R.drawable.sparking_desire;

            case "anxiety_and_fertility":
                return R.drawable.anxiety_and_fertility;

            case "impact_of_sexual_activity_on_health_and_well_being":
                return R.drawable.impact_of_sexual_activity_on_health_and_well_being;

            case "the_5_ps_of_sexual_health":
                return R.drawable.the_5_ps_of_sexual_health;

            case "food_for_period_cramp_relief":
                return R.drawable.food_for_period_cramp_relief;

            case "period_pain_without_period":
                return R.drawable.period_pain_without_period;

            case "juice_during_periods":
                return R.drawable.juice_during_periods;

            case "fast_home_period_pain_relief":
                return R.drawable.fast_home_period_pain_relief;

            case "menstrual_cramp_causes":
                return R.drawable.menstrual_cramp_causes;

            case "premature_ejaculation_in_men":
                return R.drawable.premature_ejaculation_in_men;

            case "reduce_heavy_bleeding":
                return R.drawable.reduce_heavy_bleeding;

            case "stress_and_heavy_periods":
                return R.drawable.stress_and_heavy_periods;

            case "stress_and_egg_quality":
                return R.drawable.stress_and_egg_quality;

            case "periods_and_emotional_changes":
                return R.drawable.periods_and_emotional_changes;

            case "crying_in_ovulation":
                return R.drawable.crying_in_ovulation;

            case "period_and_depression":
                return R.drawable.period_and_depression;

            case "periods_and_mental_health":
                return R.drawable.periods_and_mental_health;

            case "ovulation_depression":
                return R.drawable.ovulation_depression;

            case "pms_depression":
                return R.drawable.pms_depression;

            case "high_bleeding_in_pregnancy":
                return R.drawable.high_bleeding_in_pregnancy;

            case "high_bleeding_and_hormonal_imbalance":
                return R.drawable.high_bleeding_and_hormonal_imbalance;

            case "home_sperm_count_check":
                return R.drawable.home_sperm_count_check;

            case "fertility_foods":
                return R.drawable.fertility_foods;

            case "aging_and_sexual_health":
                return R.drawable.aging_and_sexual_health;

            case "common_sexual_health_problems":
                return R.drawable.common_sexual_health_problems;

            case "improve_sexual_intimacy_and_communication":
                return R.drawable.improve_sexual_intimacy_and_communication;

            case "mental_and_sexual_health":
                return R.drawable.mental_and_sexual_health;

            case "maintaining_sexual_health":
                return R.drawable.maintaining_sexual_health;

            case "enhance_sexual_pleasure":
                return R.drawable.enhance_sexual_pleasure;

            case "causes_of_weak_erection":
                return R.drawable.causes_of_weak_erection;

            case "thick_and_strong_sperm":
                return R.drawable.thick_and_strong_sperm;

            case "sex_on_period":
                return R.drawable.sex_on_period;

            case "ovulation_and_sex_sensation":
                return R.drawable.ovulation_and_sex_sensation;

            case "preventing_high_bleeding":
                return R.drawable.preventing_high_bleeding;

            case "normal_heavy_bleeding":
                return R.drawable.normal_heavy_bleeding;

            case "stopping_bleeding":
                return R.drawable.stopping_bleeding;

            case "guys_role_during_periods":
                return R.drawable.guys_role_during_periods;

            case "daily_sex_and_pregnancy":
                return R.drawable.daily_sex_and_pregnancy;

            case "ovulation_on_birth_control":
                return R.drawable.ovulation_on_birth_control;

            case "birth_control_side_effects":
                return R.drawable.birth_control_side_effects;

            case "weak_penis_cause":
                return R.drawable.weak_penis_cause;

            case "strong_sex_power":
                return R.drawable.strong_sex_power;

            case "contents_of_birth_control":
                return R.drawable.contents_of_birth_control;

            case "allergies_and_irritation_from_menstrual_products":
                return R.drawable.allergies_and_irritation_from_menstrual_products;

            case "overnight_use_of_menstrual_products":
                return R.drawable.overnight_use_of_menstrual_products;

            case "natural_birth_control":
                return R.drawable.natural_birth_control;

            case "sex_frequency_and_fertility":
                return R.drawable.sex_frequency_and_fertility;

            case "period_blood_and_men":
                return R.drawable.period_blood_and_men;

            case "husbands_support_during_periods":
                return R.drawable.husbands_support_during_periods;

            case "risky_sex_period":
                return R.drawable.risky_sex_period;

            case "safety_of_menstrual_cups":
                return R.drawable.safety_of_menstrual_cups;

            case "stress_mental_health_and_fertility":
                return R.drawable.stress_mental_health_and_fertility;

            case "age_and_fertility":
                return R.drawable.age_and_fertility;

            case "menstrual_product_safety":
                return R.drawable.menstrual_product_safety;

            case "moodiness_and_fatigue_on_birth_control":
                return R.drawable.moodiness_and_fatigue_on_birth_control;

            case "birth_control_and_pregnancy":
                return R.drawable.birth_control_and_pregnancy;

            case "foods_for_female_fertility":
                return R.drawable.foods_for_female_fertility;

            case "fertility_boosting_fruits":
                return R.drawable.fertility_boosting_fruits;

            case "avoiding_ovulation_disruptors":
                return R.drawable.avoiding_ovulation_disruptors;

            case "improving_male_fertility_and_sperm_health":
                return R.drawable.improving_male_fertility_and_sperm_health;

            case "best_menstrual_products_for_heavy_periods":
                return R.drawable.best_menstrual_products_for_heavy_periods;

            case "how_menstrual_pads_work":
                return R.drawable.how_menstrual_pads_work;

            case "menstrual_cups_for_teenagers":
                return R.drawable.menstrual_cups_for_teenagers;

            case "correct_usage_of_tampons":
                return R.drawable.correct_usage_of_tampons;

            case "choosing_the_right_menstrual_product":
                return R.drawable.choosing_the_right_menstrual_product;

            case "nourishing_menstrual_drinks":
                return R.drawable.nourishing_menstrual_drinks;

            case "vaginal_health":
                return R.drawable.vaginal_health;

            case "pms_without_period":
                return R.drawable.pms_without_period;

            case "pre_menstrual_nutrition":
                return R.drawable.pre_menstrual_nutrition;

            case "boosting_male_fertility_and_sperm_count":
                return R.drawable.boosting_male_fertility_and_sperm_count;

            case "improving_egg_quality_for_better_fertility":
                return R.drawable.improving_egg_quality_for_better_fertility;

            case "male_fertility_and_conception":
                return R.drawable.male_fertility_and_conception;

            case "boost_fertility_naturally":
                return R.drawable.boost_fertility_naturally;

            case "stop_discharge":
                return R.drawable.stop_discharge;

            case "pms_normality":
                return R.drawable.pms_normality;

            case "age_for_sex":
                return R.drawable.age_for_sex;

            case "pms_hormone":
                return R.drawable.pms_hormone;

            case "food_for_irregular_periods":
                return R.drawable.food_for_irregular_periods;

            case "period_supportive_foods":
                return R.drawable.period_supportive_foods;

            case "balance_hormones_for_pms":
                return R.drawable.balance_hormones_for_pms;

            case "improve_pms_mood":
                return R.drawable.improve_pms_mood;

            case "hormones_fertility_and_reproductive_health":
                return R.drawable.hormones_fertility_and_reproductive_health;

            case "improve_hormonal_health":
                return R.drawable.improve_hormonal_health;

            case "discharge_time":
                return R.drawable.discharge_time;

            case "smell_good":
                return R.drawable.smell_good;

            case "excessive_discharge":
                return R.drawable.excessive_discharge;

            case "clean_virgin":
                return R.drawable.clean_virgin;

            case "changing_pms":
                return R.drawable.changing_pms;

            case "ready_for_sex":
                return R.drawable.ready_for_sex;

            case "mood_and_hormonal_imbalance":
                return R.drawable.mood_and_hormonal_imbalance;

            case "smoking_and_menstrual_cycle":
                return R.drawable.smoking_and_menstrual_cycle;

            case "exercise_and_hormone_regulation":
                return R.drawable.exercise_and_hormone_regulation;

            case "smoking_and_menstrual_regularity":
                return R.drawable.smoking_and_menstrual_regularity;

            case "pms_duration":
                return R.drawable.pms_duration;

            case "abnormal_pms":
                return R.drawable.abnormal_pms;

            case "natural_pms_relief":
                return R.drawable.natural_pms_relief;

            case "avoid_pms_foods":
                return R.drawable.avoid_pms_foods;

            case "stress_and_menstrual_cycle":
                return R.drawable.stress_and_menstrual_cycle;

            case "period_and_labor_pain_comparison":
                return R.drawable.period_and_labor_pain_comparison;

            case "cramps_explained":
                return R.drawable.cramps_explained;

            case "hormonal_imbalances_and_weight_gain_in_girls":
                return R.drawable.hormonal_imbalances_and_weight_gain_in_girls;

            case "hormonal_health_and_weight_management":
                return R.drawable.hormonal_health_and_weight_management;

            case "stress_and_hormonal_balance":
                return R.drawable.stress_and_hormonal_balance;

            case "pain_and_fertility_connection":
                return R.drawable.pain_and_fertility_connection;

            case "marriage_effect_on_period_pain":
                return R.drawable.marriage_effect_on_period_pain;

            case "duration_of_menstrual_pain":
                return R.drawable.duration_of_menstrual_pain;

            case "managing_menstrual_pain_at_work_or_school":
                return R.drawable.managing_menstrual_pain_at_work_or_school;

            case "lifestyle_and_fertility":
                return R.drawable.lifestyle_and_fertility;

            case "sleep_and_menstrual_cycle":
                return R.drawable.sleep_and_menstrual_cycle;

            case "weight_and_menstrual_cycle":
                return R.drawable.weight_and_menstrual_cycle;

            case "diet_and_menstrual_cycle":
                return R.drawable.diet_and_menstrual_cycle;

            case "reducing_menstrual_pain":
                return R.drawable.reducing_menstrual_pain;

            case "painful_periods_and_fertility":
                return R.drawable.painful_periods_and_fertility;

            case "yogas_potential_impact_on_menstrual_regularity":
                return R.drawable.yogas_potential_impact_on_menstrual_regularity;

            case "yoga_and_blood_flow_during_menstruation":
                return R.drawable.yoga_and_blood_flow_during_menstruation;

            case "maintaining_energy_and_activity_during_menstruation":
                return R.drawable.maintaining_energy_and_activity_during_menstruation;

            case "anxiety_and_menstrual_pain":
                return R.drawable.anxiety_and_menstrual_pain;

            case "natural_relief_for_menstrual_pain":
                return R.drawable.natural_relief_for_menstrual_pain;

            case "yoga_and_exercises_impact_on_duration_and_flow_of_periods":
                return R.drawable.yoga_and_exercises_impact_on_duration_and_flow_of_periods;

            case "yoga_and_exercise_for_enhanced_fertility_during_ovulation":
                return R.drawable.yoga_and_exercise_for_enhanced_fertility_during_ovulation;

            case "yoga_and_exercise_for_improved_cervical_mucus_quality":
                return R.drawable.yoga_and_exercise_for_improved_cervical_mucus_quality;

            case "yoga_and_exercise_during_periods":
                return R.drawable.yoga_and_exercise_during_periods;

            case "reducing_stress_and_promoting_relaxation_with_yoga_during_ovulation":
                return R.drawable.reducing_stress_and_promoting_relaxation_with_yoga_during_ovulation;

            case "best_sperm_for_pregnancy":
                return R.drawable.best_sperm_for_pregnancy;
            case "causes_of_irregular_periods":
                return R.drawable.causes_of_irregular_periods;
            case "alcohol_and_egg_quality":
                return R.drawable.alcohol_and_egg_quality;
            case "most_fertile_sperm":
                return R.drawable.most_fertile_sperm;
            case "normal_irregular_periods":
                return R.drawable.normal_irregular_periods;
            case "alcohol_and_period_flow":
                return R.drawable.alcohol_and_period_flow;
            case "pregnancy_and_missed_ovulation":
                return R.drawable.pregnancy_and_missed_ovulation;
            case "preparing_for_pregnancy":
                return R.drawable.preparing_for_pregnancy;
            case "high_sex_on_ovulation":
                return R.drawable.high_sex_on_ovulation;
            case "making_period_come_faster":
                return R.drawable.making_period_come_faster;
            case "watery_sperm_and_pregnancy":
                return R.drawable.watery_sperm_and_pregnancy;
            case "intercourse_timing_with_ovulation":
                return R.drawable.intercourse_timing_with_ovulation;
            case "unprotected_sex":
                return R.drawable.unprotected_sex;
            case "increasing_ovulation_chances":
                return R.drawable.increasing_ovulation_chances;
            case "fertility_symptoms":
                return R.drawable.fertility_symptoms;
            case "get_pregnant_faster":
                return R.drawable.get_pregnant_faster;
            case "folic_acid_and_egg_quality":
                return R.drawable.folic_acid_and_egg_quality;
            case "watery_sperm_pregnancy":
                return R.drawable.watery_sperm_pregnancy;
            case "irregular_periods":
                return R.drawable.irregular_periods;
            case "sperm_to_egg":
                return R.drawable.sperm_to_egg;
            case "sex_and_immune_system":
                return R.drawable.sex_and_immune_system;
            case "sperm_staying_inside":
                return R.drawable.sperm_staying_inside;
            case "hormones_and_ovulation_pain":
                return R.drawable.hormones_and_ovulation_pain;
            case "best_days_for_pregnancy":
                return R.drawable.exercise_for_menstrual_pain_and_mood_improvement;
            case "age_and_ovulation":
                return R.drawable.age_and_ovulation;
            case "male_fertility_by_age":
                return R.drawable.male_fertility_by_age;
            case "regular_menstrual_cycle":
                return R.drawable.regular_menstrual_cycle;
            case "alcohol_and_ovulation":
                return R.drawable.alcohol_and_ovulation;
            case "getting_not_pregnant":
                return R.drawable.getting_not_pregnant;
            case "hormones_and_ovary_pain":
                return R.drawable.hormones_and_ovary_pain;
            case "healthy_menstrual_cycles":
                return R.drawable.healthy_menstrual_cycles;
            case "ovulating_without_period":
                return R.drawable.ovulating_without_period;
            case "cramps_and_pregnancy":
                return R.drawable.cramps_and_pregnancy;
            case "signs_of_abnormal_period":
                return R.drawable.signs_of_abnormal_period;
            case "behavior_on_ovulation":
                return R.drawable.behavior_on_ovulation;
            case "birth_control_for_periods":
                return R.drawable.birth_control_for_periods;
            case "causes_of_ovulation_pain":
                return R.drawable.causes_of_ovulation_pain;
            case "causes_of_failed_conception":
                return R.drawable.causes_of_failed_conception;
            case "pushing_period_faster":
                return R.drawable.pushing_period_faster;
            case "sex_while_sick":
                return R.drawable.sex_while_sick;
            case "best_month_for_pregnancy":
                return R.drawable.best_month_for_pregnancy;
            case "intercourse_after_ovulation":
                return R.drawable.intercourse_after_ovulation;
            case "ovulation_pain_and_infertility":
                return R.drawable.ovulation_pain_and_infertility;
            case "amount_of_sperm_for_pregnancy":
                return R.drawable.amount_of_sperm_for_pregnancy;
            case "safe_alcohol_for_fertility":
                return R.drawable.safe_alcohol_for_fertility;
            case "signs_of_irregular_periods":
                return R.drawable.signs_of_irregular_periods;
            case "factors_for_fertility":
                return R.drawable.factors_for_fertility;
            case "female_fertility":
                return R.drawable.female_fertility;
            case "leaving_sperm_overnight":
                return R.drawable.leaving_sperm_overnight;
            case "egg_released":
                return R.drawable.egg_released;
            case "increasing_chances_of_conception":
                return R.drawable.increasing_chances_of_conception;
            case "late_period_remedies":
                return R.drawable.late_period_remedies;
            case "color_of_ovulation_discharge":
                return R.drawable.color_of_ovulation_discharge;
            case "signs_of_late_period":
                return R.drawable.signs_of_late_period;
            case "solving_irregular_periods":
                return R.drawable.solving_irregular_periods;
            default:
                Log.e("MYTAG", "ErrorNo: setImage=:" + s);
                return R.drawable.exercise_for_menstrual_pain_and_mood_improvement;

        }
    }
}
