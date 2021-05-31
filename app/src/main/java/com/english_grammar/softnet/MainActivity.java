package com.english_grammar.softnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String question_string="I am writing a letter.,I am going to write an application.,They are building a house,The boys are playing cricket in the field .,The boys are playing cricket  today.,I can do this.,He has written all the letters.,The teacher has appointed him captain.,A thief has stolen his clothes.,He has written the poem.,Geeta has invited us.,I have posted the letter.,He is writing a letter.,Mr Das is teaching me English.,The teacher is correcting our copies.,Mother is cooking rice,My aunt is cooking rice now.,The postman is delivering the letters.,The doctor is examining the patient.,A doctor is examining the patients.,The gardener is watering the plants.,He is helping  the poors.,She is buying two sarees.,He may insult you.,Some one must do it.,We must do our duty.,You must write the answer in ink.,You must write the answer in ink.,I must fill in the form.,You must finish the work.,He reads a book everyday.,My friend gave me a letter.,I wrote a letter to the Headmaster.,Lencho wrote a latter to God.,My father wrote  a letter to the headmaster.,Mr.saikia bought a new car last month.,He bought a pen.,He gave me a pen.,He asked a question. ,He made a remarkable discovery.,Carelessness causes accidents.,My friend sent me an S.M.S.,Our teacher buys books every month.,The police arrested the culprits.,Mohan learns English,she expects good news .,People called him kalidas.,The students elected him secretary.,They elected him president.,We selected him captain of our class.,All his friends laughed at him.,We made him captain.,We made him captain of our team.,We made him chairman of the committee.,I phoned him yesterday.,The postman delivers letters every  morning.,They asked them many questions.,They invited me to the party.,The news shocked me.,The members select the president.,Pramatta singha built the ranghar.,children like sweets.,I wrote that book.,The carelesness of the driver caused the accident,The girl killed the bird,They asked the boy many questions.,We made the child cry.,I gave her the coin.,we discussed the matter thoroughly.,Our Principle presided over the meeting.,The students organised the programme.,The students decorated the stage.,The hunter killed the tiger.,My friend wrote this letter.,People established this school in 1960.,He teaches us English .,I shall learn the lesson .,We should love our country.,We should respect out elders.,You should raise this question now.,You should  obey your parents.,The girl was writing a letter.,He was driving a new car.,The postman was delivering the letters.,The manager will give you a ticket.,Mr. john will teach me English next month .,Gouri sen will help you.,";
    private String answer_string="A latter is being written by me.,An application is going to be writen by me.,A house is being built by them.,Cricket is being played in the field by the boys.,Cricket is being played today by the boys.,This can be done by me.,All the letters have been written by him.,He has been appointed captain by the teacher.,His clothes have been stolen by a thief.,The poem has been written by him.,We have been invited by Geeta.,The letter has been posted by me.,A letter is being written by him.,I am being taught English by Mr. Das.,Our copies are being corrected by the teacher,Rice is being cooked by mother.,Rice is being cooked now by my aunt.,The letters are being delivered by the postman.,The patient is being examined by the doctor.,The patients are  being examined by a doctor.,The plants are being watered by the gardener.,The poors are being helped by him.,Two sarees are being bought by her.,You may be insulted by you.,It must be done by some one,Our duties must be done by us.,The answer must be written in ink by you.,The answer must be written in ink by you.,The form must be filled in by me.,The work must be finished by you.,A book is read everyday by him.,A latter was given to me by my friend.,A letter was written to the Headmaster by me.,A latter was written to God by Leneho.,A letter was written to the headmaster by my father.,A new car was bought last month by mr.saikia.,A pen was bought by him.,A pen was given to me by him.,A question was asked by him. ,A remarkable discovery was made by him.,Accidents are caused by carelessness.,An S. M. S. Was sent to me by my friend .,Books are bought every month by our teacher.,The culprits were arrested by the police.,English is learnt by Mohan.,Good news is expected by her.,He is called kalidas by people .,He was elected secretary by the students.,He was elected president by them.,He was elected captain of our class by us.,He was laughed at by all his friends.,He was made captain by us.,He was made captain of our team by us.,He was made chairman of the committee by us.,He was phoned yesterday by me.,Letters are delivered every day by the postman.,Many questions were asked by them.,I was invited to the party by them.,I was shocked at the news.,The president is selected by the members.,Ranghar was built by pramatta singha.,Sweets are liked by Children.,That book was written by me,The accident was caused by the carelessness of the driver.,The bird was killed by the girl.,The boy was asked many questions by them.,The child was made cry by us.,The coin was given to her by me.,The matter was discussed thoroughly by us.,The meeting was presided over by our principle.,The programme was organised by the students.,The stage was decorated by the students.,The tiger was killed by the hunter.,This book was written by my friend.,This school was established in 1960 by people.,We are taught English by him.,The lesson will be learnt by me .,Our country should be loved by us.,Our elders should be respected by us.,This question should be raised now by you.,Your parents should be obeyed by you.,A letter was being written by the girl.,A new car was being drove by him.,The letters were being delivered by the post man,A ticket will be given to you by the manager.,I shall be taught English next month by Mr. john.,You will be helped by Gouri sen.,";
    private String question_structure_string="I*Am*writing*No Other Part*A letter,I*Am*going*To write*An application,They*Are*building*No Other Part*A house,The boys*Are*playing*In the field*Cricket,The boy*Are*playing*Today*Cricket,I*Can*do *No Need*This,He*Has*written*No Other Part*All the letters,The teacher*Has*appointed*Captain*Him,A Thief*Has*stolen*No Other Part*His clothes,He*Has*written*No Other Part*The poem,Geeta*Has*invited*No Other Part*Us,I*Have*posted*No Other Part*The letter,He*Is*writing*No Other Part*A letter,Mr Das*Is*teaching*English*Me,The teacher*Is*correcting*No Other Part*Our copies,Mother*Is*cooking*No Other Part*Rice,My aunt*Is*cooking*Now*Rice,The post man*Is*delivering*No Other Part*The letters,The doctor*Is*examining*No Other Part*The patient,A doctor*Is*examining*No Other Part*The patients,The gardener*Is*watering*No Other Part*The plants,He*Is*helping*No Other Part*The poor,She*Is*buying*No Other Part*Two sarees,He*May*insult*No Need*You, Some one*Must*do*No Other Part*It,We*Must*do*No Other Part*Our duty,You*Must*write*In ink*The answer,You*Must*write*In ink*The answer,I*Must*fill in*No Need*The form,You*Must*finish*No Need*The work,He*No helping verb*reads*Every day*A book,My friend*No helping verb*gave*Me*A letter,I*No helping verb*wrote*To the Head master*A letter,Lencho*No helping verb*wrote*To God*A letter,My father*No helping verb*wrote*To the Head master*A letter,Mr. Saikia*No helping verb*bought*Last month*A new car,He*No helping verb*bought*No Other Part*A pen,He*No helping verb*gave*Me*A pen,He*No helping verb*asked*No Other Part*A question ,He*No helping verb*made*No Other Part*A remarkable discovery,Carelessness*No helping verb*causes*No Other Part*Accident,My friend*No helping verb*sent*me*An S.M.S,Our teacher*No helping verb*buys*Every month*Books,The police*No helping verb*arrested*No Other Part*The Culpits,Mohan*No helping verb*learns*No Other Part*English,She*No helping verb*expects*No Other Part*Good news,People*No helping verb*called*Kalidas*Him,The students*No helping verb*elected*Secretary*Him,They*No helping verb*elected*President*Him,We*No helping verb*selected*Captain of our class*Him,All his friends*No helping verb*laughed at*No Other Part*Him,We*No helping verb*made*Captain*Him,We*No helping verb*made*Captain of our team*Him,We*No helping verb*made*Chairman of the committee*Him,I*No helping verb*phoned*yesterday*Him,The post man*No helping verb*delivers*Every morning*Letters,They*No helping verb*asked*them*Many questions,They*No helping verb*invited*To the party*Me,The news*No helping verb*shocked*No Other Part*Me,The members*No helping verb*selected*No Other Part*President,Pramatta Singha*No helping verb*built*No Other Part*The Ranghar,Children*No helping verb*like*No Other Part*Sweets,I*No helping verb*wrote*No Other Part*That book,The carelesness of the driver*No helping verb*caused*No Other Part*The accident,The girl*No helping verb*killed*No Other Part*The bird,They*No helping verb*asked*Many questions*The boy,We*No helping verb*made*Cry*The child,I*No helping verb*gave*her*The coin,We*No helping verb*discussed*Thoroughly*The matter,Our principle*No helping verb*presided over*No Other Part*The meeting,The students*No helping verb*organised*No Other Part*The programme,The students*No helping verb*decorated*No Other Part*The stage,The hunter*No helping verb*killed*No Other Part*The tiger,My friend*No helping verb*wrote*No Other Part*This letter,People*No helping verb*established*In 1960*This school,He*No helping verb*teaches*English*Us,I*Shall*learn*No Other Part*The lesson,We*Should*love*No Need*Our country,We*Should*respect*No Need*Our elders,You*Should*raise*Now*This question,You*Should*obey*Now*Your parents,    The girl*Was*writing*No Other Part*A letter,He*Was*driving*No Other Part*A new car,The post man*Was*delivering*No Other Part*The letters,The manager*Will*give*you*A ticker,Mr John*Will*teach*English next month*Me,Gouri Sen*Will*help*No Other Part*You";
    private String answer_structure_string="A letter*is*being written*No Need*By - Me,An application*Is*going to be written*No Need*By - Me,A house*Is*being built*No Need*By - Them,Cricket*Is*being played*In the field*By - The boys,Cricket*Is*being played*today*By - The boys,This*Can*be done*No Need*By - Me,All the letters*Have*been written*No Need*By - Him,He*Has*been appointed*Captain*By - the teacher,His clothes*Have*been stolen*No Need*By - Thief,The poem*Has*been written*No Need*By - Him,We*Have*been invited*No Need*By - Geeta,The letter*Has*been posted*No Need*By - Me,A letter*Is*being written*No Need*By - Him,I*Am*being taught*English*By - Mr Das,Our copies*Are*being corrected*No Need*By - The teacher,Rice*Is*being cooked*No Need*By - Mother,Rice*Is*being cooked*Now*By - My aunt,The letters*Are*being delivered*No Need*By - The post man,The patient*Is*being examined*No Need*By - The doctor,The patients*Are*being examined*No Need*By - A doctor,The plants*Are*being watered*No Need*By - The gardener,The poors*Are*being helped*No Need*By - Him,Two sarees*Are*being bought*No Need*By - Her,You*May*be insulted*No Need*By - You,    It*Must*be done*No Need*By - Some one,Our duties*Must*be done*No Need*By - Us,The answer*Must*be written*In ink*By - You,The answer*Must*be written*In ink*By - You,The form*Must*be filled*in*By - Me,The work*Must*be finished*No Need*By - You,A book*Is*read*Every day*By - Him,A letter*Was*given*To me*By - My friend,A letter*Was*written*To the Head master*By - Me,A letter*Was*written*To God*By - Lencho,A letter*Was*written*To the Head master*By - My father,A new car*Was*bought*Last month*By - Mr. Saikia,A pen*Was*bought*No Need*By - Him,A pen*Was*given*To me*By - Him,A question *Was*asked*No Need*By - Him,A remarkable discovery*was*made*No Need*By - Him,Accidents*Are*caused*No Need*By - Carelessness,An S.M.S*Was*sent*To me*By - My friend,Books*Are*bought*Every month*By - Our teacher,Culpits*Were*arrested*No Need*By - The police,English*Is*learnt*No Need*By - Mohan,Good news*Is*expected*No Need*By - Her,He*Was*called*Kalidas*By - People,He*Was*elected*Secretary*By - The students,He*Was*elected*President*By - Them,He*Was*elected*Captain of our class.*By - Us,He*Was*laughed at*No Need*By - All his friends,He*Was*made*Captain*By - Us,He*Was*made*Captain of our team*By - Us,He*Was*made*Chairman of the committee*By - Us,He*Was*phoned*Yesterday*By - Me,Letters*Are*delivered*Every day*By - The post man,Many questions*Were*asked*No Need*By - Them,I*Was*invited*To the party*By - Them,I*Was*shocked*No Need*By - The news,President*Is*selected*No Need*By - The members,Ranghar*Was*built*No Need*By - Pramatta Singha,Sweets*Are*liked*No Need*By - Children,That book*Was*written*No Need*By - Me,The accident*Was*caused*No Need*By - The carelesness of the driver,The bird*Was*killed*No Need*By - The girl,The boy*Was*asked*Many questions*By - Them,The child*Was*made*Cry*By - Us,The coin*Was*given*To her*By - Me,The matter*Was*discussed*Thoroughly*By - Us,The meeting*Was*presided over*No Need*By - Our principle,The programme*Was*organised*No Need*By - The students,The stage*Was*decorated*No Need*By - The students,The tiger*Was*killed*No Need*By - The hunter,This book*Was*written*No Need*By - My friend,This school*Was*established*In 1960*By - People,We*Are*taught*English*By - Him,The lesson*Will*be learnt*No Need*By - Me,Our country*Should*be loved*No Need*By - Us,Our elders*Should*be respected*No Need*By - Us,This question*Should*be raised*No Need*By - You,Your parents*Should*be obeyed*No Need*By - You,A letter*Was*being written*No Need*By - The girl,A new car*Was*being drove*No Need*By - Him,The letters*Were*being delivered*No Need*By - The post man,A ticket*Will*be given*To you*By - The manager,I*Shall*be taught*English next month*By - Mr John,You*Will*be helped*No Need*By - Gouri Sen";
    private Animation zoom1, zoom2,zoom3,zoom4,zoom6,slid_down,zoom_out;
    private Button hp_dialog_btn,check_btn,tap_button,positive_active_buttons,button2,positive_passive_buttons,interrogative_active_buttons,interrogative_passive_buttons,negative_active_buttons,negative_passive_buttons,wh_active_buttons,wh_passive_buttons;

    private ArrayAdapter<String> adapter_for_question;
    private ArrayAdapter<String> adapter_for_subject_question;
    private ArrayAdapter<String> adapter_for_helping_question;
    private ArrayAdapter<String> adapter_for_mainverb_question;
    private ArrayAdapter<String> adapter_for_otherpart_question;
    private ArrayAdapter<String> adapter_for_object_question;
    private ArrayAdapter<String> adapter_for_list3;

    private ArrayAdapter<String> adapter_for_subject_answer;
    private ArrayAdapter<String> adapter_for_helping_answer;
    private ArrayAdapter<String> adapter_for_mainverb_answer;
    private ArrayAdapter<String> adapter_for_otherpart_answer;
    private ArrayAdapter<String> adapter_for_object_answer;

    private ArrayAdapter<String> adapter_for_question_structure;
    private ArrayAdapter<String> adapter_for_answer_structure;
    private ArrayAdapter<String> adapter_for_answer;
    private ArrayAdapter<String> adapter_for_hp;
    private int lay_int=0;
    private int list2_position=0;
    private Dialog epicdialog_update;

    private String[] active_serial,active_question,active_subject,active_helpingVerb,active_verb,active_otherPart,active_object,active_structure,active_answer,verb1,verb2,verb3,hp;
    private CheckBox does_box_active,does_box_passive,present_indifinite_active_box,past_indifinite_active_box,present_continuas_active_box,past_continuas_active_box,present_perfect_active_box,future_indifinite_active_box,present_indifinite_passive_box,past_indifinite_passive_box,present_continuas_passive_box,past_continuas_passive_box,present_perfect_passive_box,future_indifinite_passive_box;
    private TextView test,select_texts,lt1,lt2,lt3,lt4,lt5,lt6,question_tt,hp_dialog_txt;
    private ConstraintLayout lays1,lays2,lays3;
    TextToSpeech speeches;
    private int backpress=0;
    private int zoom_int=0;
    private int zoom_int2=0;
    private int zoom_int3=0;
    private int pos=0;
    private int d1=0;
    private int position_int=0;
    private int check_box_int=0;
    private MediaPlayer play;
    private ListView lists1,lists3;
    private String select_string,select_string2,pp,pp2;
    private TextView question_tx,structure_question_tx,subject_question_tx,helping_question_tx,mainverb_question_tx,otherpart_question_tx,object_question_tx;
    private TextView answer_tx,structure_answer_tx,subject_answer_tx,helping_answer_tx,mainverb_answer_tx,otherpart_answer_tx,object_answer_tx;
    private String[] subject_question_string,helping_question_string,mainverb_question_string,otherpart_question_string,object_question_string;
    private String questions_string,subject_question_tx_speek,helping_question_tx_speek,mainverb_question_tx_speek,otherpart_question_tx_speek,object_question_tx_speek;
    private String subject_answer_tx_speek,helping_answer_tx_speek,mainverb_answer_tx_speek,otherpart_answer_tx_speek,object_answer_tx_speek;
    private String[] list3_string,subject_answer_string,helping_answer_string,mainverb_answer_string,otherpart_answer_string,object_answer_string,answers_string,answer_string_structure;
    private ConstraintLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
    private int MY_DATA_CHECK_CODE = 0;
    private CountDownTimer countDownTimer,countDownTimer2,countDownTimer3,countDownTimer4;
    private Boolean check_timer=false;
    private Boolean check_timer2=false;
    private Boolean check_timer3=false;
    private String is_string,was_string,have_string,shall_string;
    public static final String save_login="sharedPrefs1";
    public static final String phone_no="nothing1";

    public static final String save_login1="sharedPrefs2";
    public static final String phone_no1="nothing2";
    private Boolean time_cheker=false;
    private int ads_time_int=0;
    private int milis=50000;
    private int save = 0;


    private AdView adView;
    private InterstitialAd interstitialAd;
    private final String TAG = InterstitialAdActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        //setContentView(R.layout.activity_main);

        is_string="(a) Is = It is generally used in subject \"singular number\".  ( ইহা সাধারণত এক বচনে ব্যাবহৃত হয়। ) \\n(b) Am = It is generally used with subject \"I\". ( ইহা সাধারণত \"I\" Subject তে ব্যাবহৃত হয়।\" ), \\n(c) Are = It is generally used in subject \"Plural number\" and with \"You\". ( ইহা সাধারণত বহু বচনে এবং \"You\" Subject তে ব্যাবহৃত হয়। )";
        was_string="(a) was = It is generally used in subject \"singular number\". ( ইহা সাধারণত এক বচনে ব্যাবহৃত হয়। )\\n\\n(b) were = It is generally used in subject \"Plural number\". ( ইহা সাধারণত বহু বচনে Subject তে ব্যাবহৃত হয়।\" )";
        have_string="(a) Has = It is generally used in subject \"3rd person singular number\". ( ইহা সাধারণত 3rd Person Singular Subject তে ব্যবহৃত  হয়।)\\n\\n(b) Have = It is generally used all subjects except \"3rd person singular number\". ( ইহা সাধারণত 3rd Person Singular Subject ছাড়া সকল Subject তে ব্যবহৃত হয়।\\n\\n(c) Had= It is generally used in every kind of Subjects. ( ইহা সাধারণত সকল ধরণের Subject তে ব্যবহৃত হয়। )";
        shall_string="(a) Shall = It is generally used in subjects \"I and We\". But if the sentence Express surety then it is used all subjects except \"I and We\" .\n" +
                "( ইহা সাধারণত \"I\"  এবং \"We\" তে ব্যাবহৃত হয়। কিন্তু বাক্যটি যদি নিশ্চয়তা বোঝাই তখন Subject টি \"I\" বা \"We\" না থাকিলেও Shall হয়। )\\n\\n(b) Will = It is generally used in all kind of subjects except \"I and We\". But if the sentence Express surety then it is used in subjects \"I and We\" . ( ইহা সাধারণত \"I\"  এবং \"We\" না থাকলে  ব্যাবহৃত হয়। কিন্তু বাক্যটি যদি নিশ্চয়তা বোঝাই তখন Subject টি \"I\" বা \"We\"  থাকিলেও Will হয়। )";


        //test=findViewById(R.id.textView);

        lays2=findViewById(R.id.lay2);
        present_indifinite_active_box=findViewById(R.id.checkBox8);
        past_indifinite_active_box=findViewById(R.id.checkBox9);
        present_continuas_active_box=findViewById(R.id.checkBox10);
        past_continuas_active_box=findViewById(R.id.checkBox11);
        present_perfect_active_box=findViewById(R.id.checkBox12);
        future_indifinite_active_box=findViewById(R.id.checkBox13);
        present_indifinite_passive_box=findViewById(R.id.checkBox14);
        past_indifinite_passive_box=findViewById(R.id.checkBox15);
        present_continuas_passive_box=findViewById(R.id.checkBox16);
        past_continuas_passive_box=findViewById(R.id.checkBox17);
        present_perfect_passive_box=findViewById(R.id.checkBox18);
        future_indifinite_passive_box=findViewById(R.id.checkBox19);
        does_box_active=findViewById(R.id.checkBoxA);
        does_box_passive=findViewById(R.id.checkBoxB);
        lays1=findViewById(R.id.constraintLayout);

        select_texts=findViewById(R.id.select_txt);
        lists1=findViewById(R.id.list1x);
        lists3=findViewById(R.id.list3);
        lays3=findViewById(R.id.lay3);
        tap_button=findViewById(R.id.buttonss);
        check_btn=findViewById(R.id.checkbutton);
        question_tt=findViewById(R.id.textView36);



        //buttons


        question_tx=findViewById(R.id.textView6);
        structure_question_tx=findViewById(R.id.textView7);

        subject_question_tx=findViewById(R.id.textView8);
        helping_question_tx=findViewById(R.id.textView9);
        mainverb_question_tx=findViewById(R.id.textView10);
        otherpart_question_tx=findViewById(R.id.textView11);
        object_question_tx=findViewById(R.id.textView12);

        subject_answer_tx=findViewById(R.id.textView18);
        helping_answer_tx=findViewById(R.id.textView19);
        mainverb_answer_tx=findViewById(R.id.textView20);
        otherpart_answer_tx=findViewById(R.id.textView21);
        object_answer_tx=findViewById(R.id.textView22);
        answer_tx=findViewById(R.id.textView6a);
        structure_answer_tx=findViewById(R.id.textView7a);
//        hp_dialog_txt=(TextView) epicdialog_update.findViewById(R.id.textView23);
        l1=findViewById(R.id.constraintLayout7);
        l2=findViewById(R.id.constraintLayout4);
        l3=findViewById(R.id.constraintLayout5);
        l4=findViewById(R.id.constraintLayout11);
        l5=findViewById(R.id.constraintLayout10);

        l6=findViewById(R.id.constraintLayout8);
        l7=findViewById(R.id.constraintLayout16);
        l8=findViewById(R.id.constraintLayout17);
        l9=findViewById(R.id.constraintLayout18);
        l10=findViewById(R.id.constraintLayout19);

        l11=findViewById(R.id.constraintLayout15);
        l12=findViewById(R.id.constraintLayout14);
        l13=findViewById(R.id.constraintLayout13);
        l14=findViewById(R.id.constraintLayout12);
        l15=findViewById(R.id.constraintLayout9);

        lt1=findViewById(R.id.textView23);
        lt2=findViewById(R.id.textView24);
        lt3=findViewById(R.id.textView13);
        lt4=findViewById(R.id.textView14);
        lt5=findViewById(R.id.textView28);
        lt6=findViewById(R.id.textView29);

        adView = new AdView(this, "590021021508656_590029148174510", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container6);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();
        SharedPreferences sa3 = getSharedPreferences(save_login1, MODE_PRIVATE);
        ads_time_int=sa3.getInt(phone_no1,0);
        save=0;
        if (ads_time_int<1001){
            showads();
        }else {
            milis=milis-(sa3.getInt(phone_no1,0));
            time2();
        }

        active_answer=getResources().getStringArray(R.array.active_assertive_passive);
        active_structure=getResources().getStringArray(R.array.active_assertive);

        subject_question_string=getResources().getStringArray(R.array.active_assertive);
        helping_question_string=getResources().getStringArray(R.array.active_assertive);
        mainverb_question_string=getResources().getStringArray(R.array.active_assertive);
        otherpart_question_string=getResources().getStringArray(R.array.active_assertive);
        object_question_string=getResources().getStringArray(R.array.active_assertive);

        subject_answer_string=getResources().getStringArray(R.array.active_subject_passive);
        helping_answer_string=getResources().getStringArray(R.array.active_helpingVerb_passive);
        mainverb_answer_string=getResources().getStringArray(R.array.active_verb_passive);
        otherpart_answer_string=getResources().getStringArray(R.array.active_otherPert_passive);
        object_answer_string=getResources().getStringArray(R.array.active_Oject_passive);
        list3_string=getResources().getStringArray(R.array.voice);


        answers_string=getResources().getStringArray(R.array.active_Oject_passive);
        answer_string_structure=getResources().getStringArray(R.array.active_Oject_passive);
        hp=getResources().getStringArray(R.array.negative_use_of_helpingVerb);

        epicdialog_update=new Dialog(this);

        adapter_for_question = new ArrayAdapter<String>(this,R.layout.list_lay,R.id.txtlay,active_answer);
        adapter_for_question_structure = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,active_structure);

        adapter_for_subject_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subject_question_string);
        adapter_for_helping_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helping_question_string);
        adapter_for_mainverb_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mainverb_question_string);
        adapter_for_otherpart_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,otherpart_question_string);
        adapter_for_object_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,object_question_string);

        adapter_for_subject_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subject_answer_string);
        adapter_for_helping_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helping_answer_string);
        adapter_for_mainverb_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mainverb_answer_string);
        adapter_for_otherpart_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,otherpart_answer_string);
        adapter_for_object_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,object_answer_string);

        adapter_for_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,answers_string);
        adapter_for_answer_structure = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,answer_string_structure);
        adapter_for_hp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hp);
        adapter_for_list3 = new ArrayAdapter<String>(this,R.layout.list_lay2,R.id.txtlay2,list3_string);
        lists1.setAdapter(adapter_for_question);
        lists3.setAdapter(adapter_for_list3);

        speeches=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!=TextToSpeech.ERROR){
                    speeches.setLanguage(Locale.ENGLISH);
                }
            }
        });
        speeches.setSpeechRate(1);
        speeches.setPitch(1);

        zoom1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        zoom2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        zoom3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        zoom4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        zoom6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        zoom_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        slid_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        //animation.setDuration(1000);
        //test.startAnimation(animation);

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                assertive_structer();
//                present_indifinite_active_box.setText("Sub + Verb 1st Form + Other + Object");
//                lays1.setVisibility(View.GONE);
//                //lays2.setVisibility(View.VISIBLE);
//                lists1.setVisibility(View.VISIBLE);
//                //animation.setDuration(2000);
//                //test.startAnimation(animation);
//
//                speeches.speak("First, we separate the sentence into structure ",TextToSpeech.QUEUE_FLUSH,null);
//                ghh("gone");
//
//            }
//        });
        present_indifinite_active_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Sub + Verb 1st Form + Other + Object";
                }
                if (position_int==2){
                    select_string="Do + Subject + Verb 1st Form + Other + Object?";
                }
                if (position_int==3){
                    select_string="Subject  + Do + not + Verb 1st Form + Other + Object.";
                }
                select_box();
                present_indifinite_active_box.setChecked(true);
                past_indifinite_active_box.setChecked(false);
                past_continuas_active_box.setChecked(false);
            }
        });
        past_indifinite_active_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Sub +Verb 2nd Form + Other + Object";
                }
                if (position_int==2){
                    select_string="Did + Subject + Verb 1st Form + Other + Object?";
                }
                if (position_int==3){
                    select_string="Subject + Did + not + Verb 1st Form + Other + Object.";
                }
                select_box();
                past_indifinite_active_box.setChecked(true);
            }
        });
        present_continuas_active_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Sub + is/am/are + verb-ing + other + Object";
                }
                if (position_int==2){
                    select_string="Is/am/are + Subject + verb-ing + other + Object?";
                }
                if (position_int==3){
                    select_string="Subject + Is/am/are + not + verb-ing + other + Object.";
                }
                select_box();
                present_continuas_active_box.setChecked(true);
            }
        });
        past_continuas_active_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Sub + was/were + verb-ing + other + Object";
                }
                if (position_int==2){
                    select_string="Was/were + Subject + verb-ing + other + Object?";
                }
                if (position_int==3){
                    select_string="Subject + was/were + not + verb-ing + other + Object.";
                }
                select_box();
                past_continuas_active_box.setChecked(true);
            }
        });
        present_perfect_active_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Sub + have/has/had +verb 3rd Form+ other + Object";
                }
                if (position_int==2){
                    select_string="Have/has/had + Subject +verb 3rd Form+ other + Object?";
                }
                if (position_int==3){
                    select_string="Subject + Have/has/had + not +verb 3rd Form+ other + Object.";
                }
                select_box();
                present_perfect_active_box.setChecked(true);
            }
        });


        future_indifinite_active_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Sub + (shall/will)/can/may/must/should/ought to + Verb 1st Form + Other + Object";
                }
                if (position_int==2){
                    select_string="(shall/will)/can/may/must/should/ought to + Subject + Verb 1st Form + Other + Object?";
                }
                if (position_int==3){
                    select_string="Subject + (shall/will)/can/may/must/should/ought to + not + Verb 1st Form + Other + Object.";
                }
                select_box();
                future_indifinite_active_box.setChecked(true);
            }
        });
        present_indifinite_passive_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Object + is/am/are+verb-3rd Form + other + By-Subject";
                }
                if (position_int==2){
                    select_string="Is/am/are + Object + verb-3rd Form + other + By-Subject?";
                }
                if (position_int==3){
                    select_string="Object + is/am/are + not + verb-3rd Form + other + By-Subject.";
                }
                select_box();
                present_indifinite_passive_box.setChecked(true);
            }
        });
        past_indifinite_passive_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Object + was/were + verb-3rd Form + other + By-Subject";
                }
                if (position_int==2){
                    select_string="Was/were + Object + verb-3rd Form + other + By-Subject?";
                }
                if (position_int==3){
                    select_string="Object + was/were + not + verb-3rd Form + other + By-Subject.";
                }
                select_box();
                past_indifinite_passive_box.setChecked(true);
            }
        });

        present_continuas_passive_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Object + is/am/are + being + verb-3rd Form + other + By-Subject";
                }
                if (position_int==2){
                    select_string="Is/am/are + Object + being + verb 3rd form + other + By-Subject?";
                }
                if (position_int==3){
                    select_string="Object + is/am/are + not + being + verb 3rd form + other + By-Subject.";
                }
                select_box();
                present_continuas_passive_box.setChecked(true);
            }
        });
        past_continuas_passive_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Object + was/were + being + verb-3rd Form + other + By-Subject";
                }
                if (position_int==2){
                    select_string="Was/were + Object + verb-3rd Form + other + By-Subject?";
                }
                if (position_int==3){
                    select_string="Object + was/were + not + being + verb 3rd form + other + By-Subject.";
                }
                select_box();
                past_continuas_passive_box.setChecked(true);
            }
        });
        present_perfect_passive_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Object + have/has/had + been + verb-3rd Form + other + Subject";
                }
                if (position_int==2){
                    select_string="Have/has/had + Object + been + Verb 3rd Form+ other + By-Subject?";
                }
                if (position_int==3){
                    select_string="Object + have/has/had + not + been + Verb 3rd Form+ other + By-Subject.";
                }
                select_box();
                present_perfect_passive_box.setChecked(true);
            }
        });


        future_indifinite_passive_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==1){
                    select_string="Object + (shall/will)/may/must/should/ought to + be + Verb 3rd Form + Other + By-Subject";
                }
                if (position_int==2){
                    select_string="(shall/will)/can/may/must/should/ought to - be + Object + Verb 3rd Form + Other + by - subject?";
                }
                if (position_int==3){
                    select_string="Object + (shall/will)/can/may/must/should/ought to + not - be + Verb 3rd Form + Other + by - subject.";
                }
                select_box();
                future_indifinite_passive_box.setChecked(true);
            }
        });
        does_box_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==2){
                    select_string="Does + Subject + Verb 1st Form + Other + Object?";
                }
                if (position_int==3){
                    select_string="Subject + Does + not + Verb 1st Form + Other + Object.";
                }
                select_box();
                does_box_active.setChecked(true);
            }
        });
        does_box_passive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position_int==2){
                    select_string="Is/am/are + Object + verb-3rd Form + other + By-Subject?";
                }
                if (position_int==3){
                    select_string="Object + is/am/are + not + verb-3rd Form + other + By-Subject.";
                }
                select_box();
                does_box_passive.setChecked(true);
            }
        });

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_string.equals(select_string2)){
                    speeches.speak("First, we separate the sentence into structure ",TextToSpeech.QUEUE_FLUSH,null);
                    //(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                    time();
                    lays2.setVisibility(View.GONE);
                    lays3.setVisibility(View.VISIBLE);
                    backpress=2;

                }else{
                    //Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lists3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list2_position=position;
                start_value();
            }
        });
//        SharedPreferences sa2=getSharedPreferences(save_login,MODE_PRIVATE);
        //list2_position=(sa2.getInt(phone_no,0));
        start_value();

        tap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_button.setVisibility(View.GONE);
                zoom_int3=1;
                time4();
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pp=adapter_for_hp.getItem(pos);
                if (position_int==1){
                    helping_v();
                }
                if (position_int==3){
                    helping_v();
                }
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position_int==2){
                    helping_v();
                }
            }
        });
        lists1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select_string2=adapter_for_question_structure.getItem(position);
                // Toast.makeText(MainActivity.this, ""+select_string2, Toast.LENGTH_SHORT).show();
                lays2.setVisibility(View.VISIBLE);
                lays1.setVisibility(View.GONE);
                //backpress=1;

                if (position_int==1){
                    assertive_structer();

                }
                if (position_int==2){
                    interrogative_structer();
                }
                if (position_int==3){
                    negative_structer();
                }
                select_box();
                select_texts.setText("Select Correct Structure");
                question_tx.setText(adapter_for_question.getItem(position));
                question_tt.setText(adapter_for_question.getItem(position));
                structure_question_tx.setText(adapter_for_question_structure.getItem(position));
                questions_string=adapter_for_question.getItem(position);

                subject_question_tx.setText(adapter_for_subject_question.getItem(position));
                helping_question_tx.setText(adapter_for_helping_question.getItem(position));

                mainverb_question_tx.setText(adapter_for_mainverb_question.getItem(position));
                otherpart_question_tx.setText(adapter_for_otherpart_question.getItem(position));
                object_question_tx.setText(adapter_for_object_question.getItem(position));

                subject_question_tx_speek=(adapter_for_subject_question.getItem(position));
                helping_question_tx_speek=(adapter_for_helping_question.getItem(position));
                mainverb_question_tx_speek=(adapter_for_mainverb_question.getItem(position));
                otherpart_question_tx_speek=(adapter_for_otherpart_question.getItem(position));
                object_question_tx_speek=(adapter_for_object_question.getItem(position));

                subject_answer_tx.setText(adapter_for_subject_answer.getItem(position));
                helping_answer_tx.setText(adapter_for_helping_answer.getItem(position));
                mainverb_answer_tx.setText(adapter_for_mainverb_answer.getItem(position));
                otherpart_answer_tx.setText(adapter_for_otherpart_answer.getItem(position));
                object_answer_tx.setText(adapter_for_object_answer.getItem(position));

                subject_answer_tx_speek=(adapter_for_subject_answer.getItem(position));
                helping_answer_tx_speek=(adapter_for_helping_answer.getItem(position));
                mainverb_answer_tx_speek=(adapter_for_mainverb_answer.getItem(position));
                otherpart_answer_tx_speek=(adapter_for_otherpart_answer.getItem(position));
                object_answer_tx_speek=(adapter_for_object_answer.getItem(position));

                answer_tx.setText(adapter_for_answer.getItem(position));
                structure_answer_tx.setText(adapter_for_answer_structure.getItem(position));
                pp2=adapter_for_hp.getItem(position);
                Toast.makeText(MainActivity.this, ""+pp2, Toast.LENGTH_SHORT).show();
                backpress=3;

                zoom_int=1;
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);

                l6.setVisibility(View.GONE);
                l7.setVisibility(View.GONE);
                l8.setVisibility(View.GONE);
                l9.setVisibility(View.GONE);
                l10.setVisibility(View.GONE);

                l11.setVisibility(View.GONE);
                l12.setVisibility(View.GONE);
                l13.setVisibility(View.GONE);
                l14.setVisibility(View.GONE);
                l15.setVisibility(View.GONE);
                structure_question_tx.setVisibility(View.INVISIBLE);
                structure_answer_tx.setVisibility(View.INVISIBLE);
                //countDownTimer.cancel();

                tap_button.setVisibility(View.GONE);
                if (check_timer){
                    countDownTimer.cancel();
                    check_timer=false;
                }
                if (check_timer2){
                    countDownTimer2.cancel();
                    check_timer2=false;
                }
                if (check_timer3){
                    countDownTimer3.cancel();
                    check_timer3=false;
                }
                if (check_box_int==1){
                    does_box_active.setVisibility(View.VISIBLE);
                    does_box_passive.setVisibility(View.VISIBLE);
                }else {
                    does_box_active.setVisibility(View.GONE);
                    does_box_passive.setVisibility(View.GONE);
                }
                SharedPreferences sa3 = getSharedPreferences(save_login1, MODE_PRIVATE);
                ads_time_int=sa3.getInt(phone_no1,0);
                Toast.makeText(MainActivity.this, "Time"+ads_time_int, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void song(){
        stopsong();
        if(play==null){
            play= MediaPlayer.create(this,R.raw.shorts);
        }
        play.start();
    }
    public void stopsong(){
        if (play!=null){
            play.release();
            play=null;
        }
    }
    private void update_dialouge(){
        epicdialog_update.setContentView(R.layout.add_money_dialouge);

        epicdialog_update.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicdialog_update.show();
        epicdialog_update.setCancelable(true);
        hp_dialog_txt=(TextView) epicdialog_update.findViewById(R.id.textView23);
        hp_dialog_btn=(Button) epicdialog_update.findViewById(R.id.activation_button);
        hp_dialog_txt.setText(pp.replace("\\n","\n"));
        hp_dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicdialog_update.cancel();
            }
        });
    }
    public void helping_v(){
        if (pp2.equals("is")){
            pp=is_string;
        }else {
            if (pp2.equals("was")){
                pp=was_string;
            }else {
                if (pp2.equals("have")){
                    pp=have_string;
                }else {
                    if (pp2.equals("shall")){
                        pp=shall_string;
                    }else {
                        pp="It is generally used in every kind of Subjects. ( ইহা সাধারণত সকল ধরণের Subject তে ব্যবহৃত হয়। )";
                    }
                }
            }
        }
        update_dialouge();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                speeches = new TextToSpeech(this, (TextToSpeech.OnInitListener) this);
                Toast.makeText(this, "111111111111111111", Toast.LENGTH_SHORT).show();
            } else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
                Toast.makeText(this, "222222222222222222222222", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void time(){
        countDownTimer3=new CountDownTimer(4000,250){
            @Override
            public void onTick(long l) {
                switch (zoom_int){
                    case 1:
                        l6.startAnimation(zoom1);
                        l6.setVisibility(View.VISIBLE);
                        zoom_int=2;
                        break;
                    case 2:
                        l7.startAnimation(zoom2);
                        l7.setVisibility(View.VISIBLE);
                        zoom_int=3;
                        break;
                    case 3:
                        l8.startAnimation(zoom3);
                        l8.setVisibility(View.VISIBLE);
                        zoom_int=4;
                        break;
                    case 4:
                        l9.startAnimation(zoom4);
                        l9.setVisibility(View.VISIBLE);
                        zoom_int=5;
                        break;
                    case 5:
                        zoom6.setDuration(1500);
                        l10.startAnimation(zoom6);
                        l10.setVisibility(View.VISIBLE);
                        zoom_int=0;
                        break;
                }

            }
            @Override
            public void onFinish() {
                time3();
                zoom_int2=1;
            }
        }.start();
        check_timer3=true;
    }
    public void time3(){
        countDownTimer=new CountDownTimer(21000, 3500) {
            @Override
            public void onTick(long l) {
                switch (zoom_int2){
                    case 1:
                        if (lay_int==1){
                            speeches.speak(","+subject_question_tx_speek+", is selected as helping verb",TextToSpeech.QUEUE_FLUSH,null);
                        }else {
                            speeches.speak(subject_question_tx_speek+", is selected as subject",TextToSpeech.QUEUE_FLUSH,null);
                        }

                        //song();
                        l1.setVisibility(View.VISIBLE);
                        l1.startAnimation(zoom1);
                        zoom_int2=zoom_int2+1;
                        break;
                    case 2:
                        if (lay_int==1){
                            speeches.speak(helping_question_tx_speek+", is selected as subject",TextToSpeech.QUEUE_FLUSH,null);
                        }else {
                            speeches.speak(","+helping_question_tx_speek+", is selected as helping verb",TextToSpeech.QUEUE_FLUSH,null);
                        }

                        //song();
                        l2.setVisibility(View.VISIBLE);
                        l2.startAnimation(zoom1);
                        zoom_int2=zoom_int2+1;
                        break;
                    case 3:
                        speeches.speak(mainverb_question_tx_speek+", is selected as Main verb ",TextToSpeech.QUEUE_FLUSH,null);
                        //song();
                        l3.setVisibility(View.VISIBLE);
                        l3.startAnimation(zoom1);
                        zoom_int2=zoom_int2+1;
                        break;
                    case 4:
                        speeches.speak(otherpart_question_tx_speek+", is selected as other part",TextToSpeech.QUEUE_FLUSH,null);
                        //song();
                        l4.setVisibility(View.VISIBLE);
                        l4.startAnimation(zoom1);
                        zoom_int2=zoom_int2+1;
                        break;
                    case 5:
                        speeches.speak(object_question_tx_speek+", is selected as object",TextToSpeech.QUEUE_FLUSH,null);
                        //song();
                        l5.setVisibility(View.VISIBLE);
                        l5.startAnimation(zoom1);
                        zoom_int2=zoom_int2+1;
                        break;
                    case 6:
                        speeches.speak(", here the question structure",TextToSpeech.QUEUE_FLUSH,null);
                        //song();

                        structure_question_tx.setVisibility(View.VISIBLE);
                        zoom_out.setDuration(2000);
                        structure_question_tx.startAnimation(zoom_out);
                        zoom_int2=zoom_int2+1;
                        break;

                }

            }
            @Override
            public void onFinish() {
                taptarget_view();
                tap_button.setVisibility(View.VISIBLE);
                speeches.speak(", Click here, to know how to find out the answer",TextToSpeech.QUEUE_FLUSH,null);
                song();
            }
        }.start();
        check_timer=true;

    }
    public void time4(){
        countDownTimer2=new CountDownTimer(21000, 3500) {
            @Override
            public void onTick(long l) {
                switch (zoom_int3){
                    case 1:
                        if (lay_int==1){
                            speeches.speak(subject_answer_tx_speek+", is selected as helping verb",TextToSpeech.QUEUE_FLUSH,null);
                        }else {
                            speeches.speak(subject_answer_tx_speek+", is selected as subject",TextToSpeech.QUEUE_FLUSH,null);
                        }

                        //song();
                        l11.setVisibility(View.VISIBLE);
                        l11.startAnimation(zoom1);
                        zoom_int3=zoom_int3+1;
                        break;
                    case 2:
                        if (lay_int==1){

                            speeches.speak(helping_answer_tx_speek+", is selected as subject",TextToSpeech.QUEUE_FLUSH,null);
                        }else {
                            speeches.speak(helping_answer_tx_speek+", is selected as helping verb",TextToSpeech.QUEUE_FLUSH,null);
                        }
                        //song();
                        l12.setVisibility(View.VISIBLE);
                        l12.startAnimation(zoom1);
                        zoom_int3=zoom_int3+1;
                        break;
                    case 3:
                        speeches.speak(mainverb_answer_tx_speek+", is selected as Main verb ",TextToSpeech.QUEUE_FLUSH,null);
                        //song();
                        l13.setVisibility(View.VISIBLE);
                        l13.startAnimation(zoom1);
                        zoom_int3=zoom_int3+1;
                        break;
                    case 4:
                        speeches.speak(otherpart_answer_tx_speek+", is selected as other part",TextToSpeech.QUEUE_FLUSH,null);
                        //song();
                        l14.setVisibility(View.VISIBLE);
                        l14.startAnimation(zoom1);
                        zoom_int3=zoom_int3+1;
                        break;
                    case 5:
                        speeches.speak(object_answer_tx_speek+", is selected as object",TextToSpeech.QUEUE_FLUSH,null);
                        //song();
                        l15.setVisibility(View.VISIBLE);
                        l15.startAnimation(zoom1);
                        zoom_int3=zoom_int3+1;
                        break;
                    case 6:
                        speeches.speak(", here the answer structure",TextToSpeech.QUEUE_FLUSH,null);
                        //song();

                        structure_answer_tx.setVisibility(View.VISIBLE);
                        zoom_out.setDuration(2000);
                        structure_answer_tx.startAnimation(zoom_out);
                        zoom_int3=zoom_int3+1;
                        break;

                }

            }
            @Override
            public void onFinish() {
                speeches.speak(", Click here, to know how to use of helping verb",TextToSpeech.QUEUE_FLUSH,null);
                song();
                if (position_int==1){
                    taptarget_view2(R.id.constraintLayout16);
                }
                if (position_int==2){
                    taptarget_view2(R.id.constraintLayout8);
                }
                if (position_int==3){
                    taptarget_view2(R.id.constraintLayout16);
                }
//                tap_button.setVisibility(View.VISIBLE);

            }
        }.start();
        check_timer2=true;
    }

    public void taptarget_view() {

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.buttonss), "Click Here ", "Click here, to know how to find out the answer of the question in details, Question =?")

                        .tintTarget(false)
                        .descriptionTextColor(R.color.red)  // Specify the color of the description text
                        .titleTextSize(45)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.black)      // Specify the color of the title text
                        .descriptionTextSize(13)
                        .dimColor(R.color.black)
                        .targetCircleColor(R.color.red)
                        .drawShadow(true)
                        .outerCircleColor(R.color.yellow),
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        tap_button.setVisibility(View.GONE);
                        zoom_int3=1;
                        time4();
                    }
                });

    }
    public void taptarget_view2(int a) {

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(a), "Click Here ", "Click here, to know the use of helping verbs")

                        .tintTarget(false)
                        .descriptionTextColor(R.color.white)  // Specify the color of the description text
                        .titleTextSize(45)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(13)
                        .dimColor(R.color.black)
                        .targetCircleColor(R.color.white)
                        .drawShadow(true)
                        .outerCircleColor(R.color.red),
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        update_dialouge();

                    }
                }
        );

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        switch (backpress){
            case 0:
                if (time_cheker){
                    SharedPreferences sa3 = getSharedPreferences(save_login1, MODE_PRIVATE);
                    countDownTimer.cancel();
                    ads_time_int=sa3.getInt(phone_no1,0);
                    //Toast.makeText(MainActivity2.this, "Time"+ads_time_int, Toast.LENGTH_SHORT).show();
                }
                open_another_screen();
                finish();
                break;
            case 1:
//                lays1.setVisibility(View.VISIBLE);
//                lists1.setVisibility(View.GONE);
                lt1.setText("- It is selected as Subject");
                lt2.setText("- It is selected as Helping verb");
                lt3.setText("Subject");
                lt4.setText("Helping Verb");
                lt5.setText("- It is selected as Subject");
                lt6.setText("- It is selected as Helping verb");
                backpress=0;
                lay_int=0;
                break;
            case 2:
                lays3.setVisibility(View.GONE);
                lays1.setVisibility(View.VISIBLE);
                backpress=1;
                if (check_timer){
                    countDownTimer.cancel();
                    check_timer=false;
                }
                if (check_timer2){
                    countDownTimer2.cancel();
                    check_timer2=false;
                }
                if (check_timer3){
                    countDownTimer3.cancel();
                    check_timer3=false;
                }
                break;
            case 3:
                lays2.setVisibility(View.GONE);
                lays1.setVisibility(View.VISIBLE);
                if (d1==0){
                    showads();
                    //shows();
                }

                select_box();
                backpress=1;
                break;
        }
    }

    public void adapter(){
        adapter_for_question = new ArrayAdapter<String>(this,R.layout.list_lay,R.id.txtlay,active_answer);
        adapter_for_question_structure = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,active_structure);
        adapter_for_subject_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subject_question_string);
        adapter_for_helping_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helping_question_string);
        adapter_for_mainverb_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mainverb_question_string);
        adapter_for_otherpart_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,otherpart_question_string);
        adapter_for_object_question = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,object_question_string);

        adapter_for_subject_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subject_answer_string);
        adapter_for_helping_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helping_answer_string);
        adapter_for_mainverb_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mainverb_answer_string);
        adapter_for_otherpart_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,otherpart_answer_string);
        adapter_for_object_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,object_answer_string);

        adapter_for_answer = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,answers_string);
        adapter_for_answer_structure = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,answer_string_structure);
        adapter_for_hp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hp);


    }
    public void ghh(Object input_msg){
        present_indifinite_active_box.setText(String.valueOf(input_msg));
    }
    public void select_box(){
        present_indifinite_active_box.setChecked(false);
        past_indifinite_active_box.setChecked(false);
        present_continuas_active_box.setChecked(false);
        past_continuas_active_box.setChecked(false);
        present_perfect_active_box.setChecked(false);
        future_indifinite_active_box.setChecked(false);
        present_indifinite_passive_box.setChecked(false);
        past_indifinite_passive_box.setChecked(false);
        present_continuas_passive_box.setChecked(false);
        past_continuas_passive_box.setChecked(false);
        present_perfect_passive_box.setChecked(false);
        future_indifinite_passive_box.setChecked(false);
        does_box_passive.setChecked(false);
        does_box_active.setChecked(false);
        select_texts.setText(select_string+"\n"+select_string2);
        select_texts.startAnimation(zoom3);

    }
    public void assertive_structer(){

        present_indifinite_active_box.setText("Sub + Verb 1st Form + Other + Object");
        past_indifinite_active_box.setText("Sub +Verb 2nd Form + Other + Object");
        present_continuas_active_box.setText("Sub + is/am/are + verb-ing + other + Object");
        past_continuas_active_box.setText("Sub + was/were + verb-ing + other + Object");
        present_perfect_active_box.setText("Sub + have/has/had +verb 3rd Form+ other + Object");
        future_indifinite_active_box.setText("Sub + (shall/will)/can/may/must/should/ought to + Verb 1st Form + Other + Object");

        present_indifinite_passive_box.setText("Object + is/am/are+verb-3rd Form + other + By-Subject");
        past_indifinite_passive_box.setText("Object + was/were + verb-3rd Form + other + By-Subject");
        present_continuas_passive_box.setText("Object + is/am/are + being + verb-3rd Form + other + By-Subject");
        past_continuas_passive_box.setText("Object + was/were + being + verb-3rd Form + other + By-Subject");
        present_perfect_passive_box.setText("Object + have/has/had + been + verb-3rd Form + other + Subject");
        future_indifinite_passive_box.setText("Object + (shall/will)/may/must/should/ought to + be + Verb 3rd Form + Other + By-Subject");
    }
    public void interrogative_structer(){

        present_indifinite_active_box.setText("Do + Subject + Verb 1st Form + Other + Object?");
        past_indifinite_active_box.setText("Did + Subject + Verb 1st Form + Other + Object?");
        present_continuas_active_box.setText("Is/am/are + Subject + verb-ing + other + Object?");
        past_continuas_active_box.setText("Was/were + Subject + verb-ing + other + Object?");
        present_perfect_active_box.setText("Have/has/had + Subject +verb 3rd Form+ other + Object?");
        future_indifinite_active_box.setText("(shall/will)/can/may/must/should/ought to + Subject + Verb 1st Form + Other + Object?");

        present_indifinite_passive_box.setText("Is/am/are + Object + verb-3rd Form + other + By-Subject?");
        past_indifinite_passive_box.setText("Was/were + Object + verb-3rd Form + other + By-Subject?");
        present_continuas_passive_box.setText("Is/am/are + Object + being + verb 3rd form + other + By-Subject?");
        past_continuas_passive_box.setText("Was/were + Object + verb-3rd Form + other + By-Subject?");
        present_perfect_passive_box.setText("Have/has/had + Object + been + Verb 3rd Form+ other + By-Subject?");
        future_indifinite_passive_box.setText("(shall/will)/can/may/must/should/ought to - be + Object + Verb 3rd Form + Other + by - subject?");

        does_box_active.setText("Does + Subject + Verb 1st Form + Other + Object?");
        does_box_passive.setText("Is/am/are + Object + verb-3rd Form + other + By-Subject?");
    }
    public void negative_structer(){

        present_indifinite_active_box.setText("Subject  + Do + not + Verb 1st Form + Other + Object.");
        past_indifinite_active_box.setText("Subject + Did + not + Verb 1st Form + Other + Object.");
        present_continuas_active_box.setText("Subject + Is/am/are + not + verb-ing + other + Object.");
        past_continuas_active_box.setText("Subject + was/were + not + verb-ing + other + Object.");
        present_perfect_active_box.setText("Subject + Have/has/had + not +verb 3rd Form+ other + Object.");
        future_indifinite_active_box.setText("Subject + (shall/will)/can/may/must/should/ought to + not + Verb 1st Form + Other + Object.");

        present_indifinite_passive_box.setText("Object + is/am/are + not + verb-3rd Form + other + By-Subject.");
        past_indifinite_passive_box.setText("Object + was/were + not + verb-3rd Form + other + By-Subject.");
        present_continuas_passive_box.setText("Object + is/am/are + not + being + verb 3rd form + other + By-Subject.");
        past_continuas_passive_box.setText("Object + was/were + not + being + verb 3rd form + other + By-Subject.");
        present_perfect_passive_box.setText("Object + have/has/had + not + been + Verb 3rd Form+ other + By-Subject.");
        future_indifinite_passive_box.setText("Object + (shall/will)/can/may/must/should/ought to + not - be + Verb 3rd Form + Other + by - subject.");

        does_box_active.setText("Subject + Does + not + Verb 1st Form + Other + Object.");
        does_box_passive.setText("Object + is/am/are + not + verb-3rd Form + other + By-Subject.");
    }
    public void start_value(){
        if (list2_position==0){
//            lays1.setVisibility(View.GONE);
            lays1.setVisibility(View.VISIBLE);
            active_answer=getResources().getStringArray(R.array.active_assertive);
            active_structure=getResources().getStringArray(R.array.assertive_stracture);

            subject_question_string=getResources().getStringArray(R.array.active_subject);
            helping_question_string=getResources().getStringArray(R.array.active_helpingVerb);
            mainverb_question_string=getResources().getStringArray(R.array.active_verb);
            otherpart_question_string=getResources().getStringArray(R.array.active_otherPert);
            object_question_string=getResources().getStringArray(R.array.active_Oject);

            subject_answer_string=getResources().getStringArray(R.array.active_subject_passive);
            helping_answer_string=getResources().getStringArray(R.array.active_helpingVerb_passive);
            mainverb_answer_string=getResources().getStringArray(R.array.active_verb_passive);
            otherpart_answer_string=getResources().getStringArray(R.array.active_otherPert_passive);
            object_answer_string=getResources().getStringArray(R.array.active_Oject_passive);

            answers_string=getResources().getStringArray(R.array.active_assertive_passive);
            answer_string_structure=getResources().getStringArray(R.array.assertive_stracture_passive);
            hp=getResources().getStringArray(R.array.assertive_use_of_helping_verb);

            adapter();
            lists1.setAdapter(adapter_for_question);
            check_box_int=0;
            position_int=1;
        }
        if (list2_position==1){
            //lays1.setVisibility(View.GONE);
            lays1.setVisibility(View.VISIBLE);
            active_answer=getResources().getStringArray(R.array.interrogative_question_active);
            active_structure=getResources().getStringArray(R.array.interrogative_structure_active);

            subject_question_string=getResources().getStringArray(R.array.interrogative_helpingVerb_active);
            helping_question_string=getResources().getStringArray(R.array.interrogative_subject_active);
            mainverb_question_string=getResources().getStringArray(R.array.interrogative_verb_active);
            otherpart_question_string=getResources().getStringArray(R.array.interrogative_otherPart_active);
            object_question_string=getResources().getStringArray(R.array.interrogative_object_active);

            subject_answer_string=getResources().getStringArray(R.array.interrogative_helpingVerb_passive);
            helping_answer_string=getResources().getStringArray(R.array.interrogative_subject_passive);
            mainverb_answer_string=getResources().getStringArray(R.array.interrogative_verb_passive);
            otherpart_answer_string=getResources().getStringArray(R.array.interrogative_otherPart_passive);
            object_answer_string=getResources().getStringArray(R.array.interrogative_object_passive);
            hp=getResources().getStringArray(R.array.interrogative_use_of_helpingVerb);
            answers_string=getResources().getStringArray(R.array.interrogative_question_active);
            answer_string_structure=getResources().getStringArray(R.array.interrogative_structure_active);
            lay_int=1;
            lt1.setText("- It is selected as Helping verb");
            lt2.setText("- It is selected as Subject");
            lt3.setText("Helping Verb");
            lt4.setText("Subject");
            lt5.setText("- It is selected as Helping verb");
            lt6.setText("- It is selected as Subject");

            adapter();
            lists1.setAdapter(adapter_for_question);
            lay_int=1;
            check_box_int=1;
            position_int=2;
        }
        if (list2_position==2){
            // lays1.setVisibility(View.GONE);
            lays1.setVisibility(View.VISIBLE);
            active_answer=getResources().getStringArray(R.array.negative_question_active);
            active_structure=getResources().getStringArray(R.array.negative_structure_active);

            subject_question_string=getResources().getStringArray(R.array.negative_subject_active);
            helping_question_string=getResources().getStringArray(R.array.negative_helpingVerb_active);
            mainverb_question_string=getResources().getStringArray(R.array.negative_verb_active);
            otherpart_question_string=getResources().getStringArray(R.array.negative_otherPart_active);
            object_question_string=getResources().getStringArray(R.array.negative_object_active);

            subject_answer_string=getResources().getStringArray(R.array.negative_subject_passive);
            helping_answer_string=getResources().getStringArray(R.array.negative_helpingVerb_passive);
            mainverb_answer_string=getResources().getStringArray(R.array.negative_verb_passive);
            otherpart_answer_string=getResources().getStringArray(R.array.negative_otherPart_passive);
            object_answer_string=getResources().getStringArray(R.array.negative_object_passive);

            answers_string=getResources().getStringArray(R.array.negative_question_passive);
            answer_string_structure=getResources().getStringArray(R.array.negative_structure_passive);
            hp=getResources().getStringArray(R.array.negative_use_of_helpingVerb);
            adapter();
            lists1.setAdapter(adapter_for_question);
            check_box_int=1;
            position_int=3;
        }
        if (list2_position==3){
            //  lays1.setVisibility(View.GONE);
            lays1.setVisibility(View.VISIBLE);
            active_answer=getResources().getStringArray(R.array.active_assertive_passive);
            active_structure=getResources().getStringArray(R.array.assertive_stracture_passive);

            subject_question_string=getResources().getStringArray(R.array.active_subject_passive);
            helping_question_string=getResources().getStringArray(R.array.active_helpingVerb_passive);
            mainverb_question_string=getResources().getStringArray(R.array.active_verb_passive);
            otherpart_question_string=getResources().getStringArray(R.array.active_otherPert_passive);
            object_question_string=getResources().getStringArray(R.array.active_Oject_passive);

            subject_answer_string=getResources().getStringArray(R.array.active_subject);
            helping_answer_string=getResources().getStringArray(R.array.active_helpingVerb);
            mainverb_answer_string=getResources().getStringArray(R.array.active_verb);
            otherpart_answer_string=getResources().getStringArray(R.array.active_otherPert);
            object_answer_string=getResources().getStringArray(R.array.active_Oject);

            answers_string=getResources().getStringArray(R.array.active_assertive);
            answer_string_structure=getResources().getStringArray(R.array.assertive_stracture);
            hp=getResources().getStringArray(R.array.assertive_use_of_helping_verb);
            adapter();
            lists1.setAdapter(adapter_for_question);
            check_box_int=0;
            position_int=1;
        }
        if (list2_position==4){
            // lays1.setVisibility(View.GONE);
            lays1.setVisibility(View.VISIBLE);
            active_answer=getResources().getStringArray(R.array.interrogative_question_passive);
            active_structure=getResources().getStringArray(R.array.interrogative_structure_passive);


            subject_question_string=getResources().getStringArray(R.array.interrogative_helpingVerb_passive);
            helping_question_string=getResources().getStringArray(R.array.interrogative_subject_passive);
            mainverb_question_string=getResources().getStringArray(R.array.interrogative_verb_passive);
            otherpart_question_string=getResources().getStringArray(R.array.interrogative_otherPart_passive);
            object_question_string=getResources().getStringArray(R.array.interrogative_object_passive);

            subject_answer_string=getResources().getStringArray(R.array.interrogative_helpingVerb_active);
            helping_answer_string=getResources().getStringArray(R.array.interrogative_subject_active);
            mainverb_answer_string=getResources().getStringArray(R.array.interrogative_verb_active);
            otherpart_answer_string=getResources().getStringArray(R.array.interrogative_otherPart_active);
            object_answer_string=getResources().getStringArray(R.array.interrogative_object_active);
            hp=getResources().getStringArray(R.array.interrogative_use_of_helpingVerb);
            answers_string=getResources().getStringArray(R.array.interrogative_question_active);
            answer_string_structure=getResources().getStringArray(R.array.interrogative_structure_active);
            lt1.setText("- It is selected as Helping verb");
            lt2.setText("- It is selected as Subject");
            lt3.setText("Helping Verb");
            lt4.setText("Subject");
            lt5.setText("- It is selected as Helping verb");
            lt6.setText("- It is selected as Subject");
            lay_int=1;
            adapter();
            lists1.setAdapter(adapter_for_question);
            check_box_int=1;
            position_int=2;
        }
        if (list2_position==5){
            //lays1.setVisibility(View.GONE);
            lays1.setVisibility(View.VISIBLE);

            active_answer=getResources().getStringArray(R.array.negative_question_passive);
            active_structure=getResources().getStringArray(R.array.negative_structure_passive);

            subject_question_string=getResources().getStringArray(R.array.negative_subject_passive);
            helping_question_string=getResources().getStringArray(R.array.negative_helpingVerb_passive);
            mainverb_question_string=getResources().getStringArray(R.array.negative_verb_passive);
            otherpart_question_string=getResources().getStringArray(R.array.negative_otherPart_passive);
            object_question_string=getResources().getStringArray(R.array.negative_object_passive);

            subject_answer_string=getResources().getStringArray(R.array.negative_subject_active);
            helping_answer_string=getResources().getStringArray(R.array.negative_helpingVerb_active);
            mainverb_answer_string=getResources().getStringArray(R.array.negative_verb_active);
            otherpart_answer_string=getResources().getStringArray(R.array.negative_otherPart_active);
            object_answer_string=getResources().getStringArray(R.array.negative_object_active);

            answers_string=getResources().getStringArray(R.array.negative_question_active);
            answer_string_structure=getResources().getStringArray(R.array.negative_structure_active);
            hp=getResources().getStringArray(R.array.negative_use_of_helpingVerb);
            adapter();
            lists1.setAdapter(adapter_for_question);
            check_box_int=1;
            position_int=3;
        }

    }
    public void open_another_screen(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void showads(){
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        interstitialAd = new InterstitialAd(this, "590021021508656_590026458174779");
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
                d1=1;
                time2();
                save=1;
                Toast.makeText(MainActivity.this, "show", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

// For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

    }
    public void ads_function(){
        SharedPreferences sa3 = getSharedPreferences(save_login1, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sa3.edit();
        editor3.putInt(phone_no1, ads_time_int);
        editor3.apply();
    }
    public void time2(){
        countDownTimer4=new CountDownTimer(milis,1000){
            @Override
            public void onTick(long l) {
                ads_time_int=ads_time_int+1000;
                ads_function();
            }
            @Override
            public void onFinish() {
                ads_time_int=0;
                d1=0;
                ads_function();
                if (save==0){
                    showads();
                }

            }
        }.start();
        time_cheker=true;
    }

}