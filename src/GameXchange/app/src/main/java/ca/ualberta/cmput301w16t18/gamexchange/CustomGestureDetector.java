package ca.ualberta.cmput301w16t18.gamexchange;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;

// modified from http://developer.android.com/training/gestures/detector.html
// and http://stackoverflow.com/questions/12713926/showing-a-delete-button-on-swipe-in-a-listview-for-android
public class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener
        implements ListView.OnTouchListener {
    private Context context;
    private ListView listView;
    private GestureDetector gestureDetector;
    private SearchListActivity searchListActivity;


    public CustomGestureDetector() {
        super();
    }

    /* This constructor is now deprecated and should not be used. */
    public CustomGestureDetector(Context context, ListView listView) {
        GestureDetector detector = new GestureDetector(context, this);

        this.context = context;
        this.listView = listView;
        this.gestureDetector = detector;
    }

    public CustomGestureDetector(SearchListActivity searchListActivity, Context context, ListView listView) {
        GestureDetector detector = new GestureDetector(context, this);

        this.context = context;
        this.listView = listView;
        this.gestureDetector = detector;
        this.searchListActivity = searchListActivity;
    }

    /* This constructor is now deprecated and should not be used. */
    public CustomGestureDetector(Context context, GestureDetector detector) {
        if(detector == null) {
            detector = new GestureDetector(context, this);
        }

        this.context = context;
        this.gestureDetector = detector;
    }

    //Conditions are going to be velocity and distance
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        final int position = listView.pointToPosition(
                Math.round(e1.getX()),
                Math.round(e1.getY())
        );

        if(e2.getX() - e1.getX() > Constants.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > Constants.SWIPE_THRESHOLD_VELOCITY) {
            //System.out.println("Swiped Right");
            if(hideEditButton(position)) {
                return true;
            }
        }
        if(e1.getX() - e2.getX() > Constants.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > Constants.SWIPE_THRESHOLD_VELOCITY) {
            //System.out.println("Swiped Left");
            if(showEditButton(position)) {
                return true;
            }
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    private boolean hideEditButton(int position) {
        View child = listView.getChildAt(position - listView.getFirstVisiblePosition());
        if(child != null) {
            Button edit = (Button) child.findViewById(R.id.SearchListEditButton);
            if(edit != null) {
                if(edit.getVisibility() == View.VISIBLE) {
                    edit.setOnClickListener(null);

                    Animation buttonSwipe = AnimationUtils.loadAnimation(child.getContext(),
                            R.anim.search_delete_button_hide_animation);
                    edit.setAnimation(buttonSwipe);
                    edit.animate();
                    edit.setVisibility(View.INVISIBLE);
                }
            }
            return true;
        }
        else {
            System.out.println("Child is null. Position: " + position);
        }
        return false;
    }

    private boolean showEditButton(final int position) {
        View child = listView.getChildAt(position - listView.getFirstVisiblePosition());
        if(child != null) {
            Button edit = (Button) child.findViewById(R.id.SearchListEditButton);
            if(edit != null) {
                if(edit.getVisibility() == View.INVISIBLE) {
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            searchListActivity.deleteGameByPosition(position);
                        }
                    });
                    Animation buttonSwipe = AnimationUtils.loadAnimation(child.getContext(),
                            R.anim.search_delete_button_show_animation);
                    edit.setAnimation(buttonSwipe);
                    edit.setVisibility(View.VISIBLE);
                    edit.animate();
                }
            }
            return true;
        }
        else {
            System.out.println("Child is null. Position: " + position);
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public GestureDetector getDetector() {
        return gestureDetector;
    }
}