package com.jh.team2.View;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;

import com.jh.team2.BackKeyHandler;
import com.jh.team2.R;
import com.jh.team2.Search;
import com.jh.team2.ViewModel.MainViewModel;

public class Team2A_FragmentActivity extends AppCompatActivity {
    private final BackKeyHandler backKeyHandler = new BackKeyHandler(Team2A_FragmentActivity.this);
    private MainViewModel viewModel;
    private final String DEFAULT = "DEFAULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2a_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewModel = new MainViewModel();
        viewModel.init(fragmentManager);

        Toolbar toolbar = findViewById(R.id.movieToolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_main).setOnClickListener(v -> viewModel.onClick(v.getId()));
        findViewById(R.id.btn_chat).setOnClickListener(v -> viewModel.onClick(v.getId()));
        findViewById(R.id.btn_pop).setOnClickListener(v -> viewModel.onClick(v.getId()));
        findViewById(R.id.btn_top).setOnClickListener(v -> viewModel.onClick(v.getId()));

        createNotificationChannel(DEFAULT, "default channel", NotificationManager.IMPORTANCE_HIGH);

        Intent intent = new Intent(this, Team2A_FragmentActivity.class);       // 클릭시 실행할 activity를 지정
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        createNotification(DEFAULT, 1, "movieRecommend", "장르별 영화 추천해드려요", intent);
    }

    void createNotificationChannel(String channelId, String channelName, int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, importance));
        }
    }

    void createNotification(String channelId, int id, String title, String text, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_movie)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(pendingIntent)    // 클릭시 설정된 PendingIntent가 실행된다
                .setAutoCancel(true)                // true이면 클릭시 알림이 삭제된다
//                .setTimeoutAfter(1000)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    // 앱 종료시 두번 누르면 종료
    @Override
    public void onBackPressed() {
        /* 다음 4가지 형태 중 하나 선택해서 사용 */

//        backKeyHandler.onBackPressed();
        backKeyHandler.onBackPressed("버튼을 한 번 더 누르면 종료됩니다.");
//        backKeyHandler.onBackPressed(5);
//        backKeyHandler.onBackPressed("5초 내로 한번 더 누르세요", 5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메인 툴바 메뉴 생성
        getMenuInflater().inflate(R.menu.movie_toolbar, menu);

        return true;
    }

    //앱바(App Bar)에 표시된 액션 또는 오버플로우 메뉴가 선택되면
    //액티비티의 onOptionsItemSelected() 메서드가 호출
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.search) {
            Intent search = new Intent(Team2A_FragmentActivity.this, Search.class);
            startActivity(search);
            return true;
        } else if (itemId == R.id.myPage) {

//            Intent mypage = new Intent(this, Mypage.class);
//
//
//            startActivity(mypage);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}