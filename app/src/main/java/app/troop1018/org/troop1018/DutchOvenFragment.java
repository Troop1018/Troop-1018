package app.troop1018.org.troop1018;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.troop1018.org.troop1018.wheel.OnWheelChangedListener;
import app.troop1018.org.troop1018.wheel.OnWheelScrollListener;
import app.troop1018.org.troop1018.wheel.WheelView;
import app.troop1018.org.troop1018.wheel.adapters.ArrayWheelAdapter;

public class DutchOvenFragment extends Fragment {

    private boolean scrolling = false;

    private WheelView sizeWheel;
    private WheelView temperatureWheel;
    private WheelView methodWheel;

    // [Simmer,Bake,Roast,Fry]
    private final int BRIQUETTES_ABOVE[][][] = {
            //   250°          275°          300°          325°          350°          375°          400°          425°          450°
            { {1,1,1,0},    {2,3,3,0},    {2,5,4,0},    {3,7,5,0},    {4,8,6,0},    {5,9,7,0},    {6,11,9,0},   {6,13,10,0},  {7,15,11,0}  }, // 5in
            { {1,3,2,0},    {2,5,4,0},    {3,6,5,0},    {4,8,6,0},    {5,9,7,0},    {5,11,8,0},   {6,13,10,0},  {7,14,11,0},  {8,16,12,0}  }, // 6in
            { {2,4,3,0},    {3,6,5,0},    {4,7,6,0},    {5,9,7,0},    {5,11,8,0},   {6,12,9,0},   {7,14,11,0},  {8,15,12,0},  {9,17,13,0}  }, // 8 in
            { {3,7,5,0},    {4,9,7,0},    {5,10,8,0},   {6,12,9,0},   {7,13,10,0},  {7,15,11,0},  {8,17,13,0},  {9,18,14,0},  {10,20,15,0} }, // 10 in
            { {4,8,6,0},    {5,10,8,0},   {6,11,9,0},   {7,13,10,0},  {7,15,11,0},  {8,16,12,0},  {9,18,14,0},  {10,19,15,0}, {11,21,16,0} }, // 10 in D
            { {5,9,7,0},    {6,11,9,0},   {6,13,10,0},  {7,15,11,0},  {8,16,12,0},  {9,17,13,0},  {10,19,15,0}, {10,21,16,0}, {11,23,17,0} }, // 12 in
            { {6,12,9,0},   {7,14,11,0},  {8,15,12,0},  {9,17,13,0},  {9,19,14,0},  {10,20,15,0}, {11,22,17,0}, {12,23,18,0}, {13,25,19,0} }, // 12 in D
            { {6,12,9,0},   {7,14,11,0},  {8,15,12,0},  {9,17,13,0},  {9,19,14,0},  {10,20,15,0}, {11,22,17,0}, {12,23,18,0}, {13,25,19,0} }, // 14 in
            { {7,13,10,0},  {8,15,12,0},  {8,17,13,0},  {9,19,14,0},  {10,20,15,0}, {11,21,16,0}, {12,23,18,0}, {12,25,19,0}, {13,27,20,0} }, // 14 in D
            { {7,15,11,0},  {8,17,13,0},  {9,18,14,0},  {10,20,15,0}, {11,21,16,0}, {11,23,17,0}, {12,25,19,0}, {13,26,20,0}, {14,28,21,0} }, // 16 in
            { {9,17,13,0},  {10,19,15,0}, {10,21,16,0}, {11,23,17,0}, {12,24,18,0}, {13,25,19,0}, {14,27,21,0}, {14,29,22,0}, {15,31,23,0} }, // 18 in
            { {10,20,15,0}, {11,22,17,0}, {12,23,18,0}, {13,25,19,0}, {13,27,20,0}, {14,28,21,0}, {15,30,23,0}, {16,31,24,0}, {17,33,25,0} }, // 20 in
            { {11,23,17,0}, {12,25,19,0}, {13,26,20,0}, {14,28,21,0}, {15,29,22,0}, {15,31,23,0}, {16,33,25,0}, {17,34,26,0}, {18,36,27,0} }  // 22 in
    };

    // [Simmer,Bake,Roast,Fry]
    private final int BRIQUETTES_BELOW[][][] = {
            //   250°           275°           300°           325°          350°            375°           400°            425°           450°
            { {1,1,1,2},     {3,2,2,5},     {5,2,3,7},     {7,3,5,10},    {8,4,6,12},    {9,5,7,14},    {11,6,8,17},   {13,6,9,19},   {15,7,11,22}  }, // 5in
            { {3,1,2,4},     {5,2,3,7},     {6,3,4,9},     {8,4,6,12},    {9,5,7,14},    {11,5,8,16},   {13,6,9,19},   {14,7,10,21},  {16,8,12,24}  }, // 6in
            { {4,2,3,6},     {6,3,4,9},     {7,4,5,11},    {9,5,7,14},    {11,5,8,16},   {12,6,9,18},   {14,7,10,21},  {15,8,11,23},  {17,9,13,26}  }, // 8 in
            { {7,3,5,10},    {9,4,6,13},    {10,5,7,15},   {12,6,9,18},   {13,7,10,20},  {15,7,11,22},  {17,8,12,25},  {18,9,13,27},  {20,10,15,30} }, // 10 in
            { {8,4,6,12},    {10,5,7,15},   {11,6,8,17},   {13,7,10,20},  {15,7,11,22},  {16,8,12,24},  {18,9,13,27},  {19,10,14,29}, {21,11,16,32} }, // 10 in D
            { {9,5,7,14},    {11,6,8,17},   {13,6,9,19},   {15,7,11,22},  {16,8,12,24},  {17,9,13,26},  {19,10,14,29}, {21,10,15,31}, {23,11,17,34} }, // 12 in
            { {12,6,9,18},   {14,7,10,21},  {15,8,11,23},  {17,9,13,26},  {19,9,14,28},  {20,10,15,30}, {22,11,16,33}, {23,12,17,35}, {25,13,19,38} }, // 12 in D
            { {12,6,9,18},   {14,7,10,21},  {15,8,11,23},  {17,9,13,26},  {19,9,14,28},  {20,10,15,30}, {22,11,16,33}, {23,12,17,35}, {25,13,19,38} }, // 14 in
            { {13,7,10,20},  {15,8,11,23},  {17,8,12,25},  {19,9,14,28},  {20,10,15,30}, {21,11,16,32}, {23,12,17,35}, {25,12,18,37}, {27,13,20,40} }, // 14 in D
            { {15,7,11,22},  {17,8,12,25},  {18,9,13,27},  {20,10,15,30}, {21,11,16,32}, {23,11,17,34}, {25,12,18,37}, {26,13,19,39}, {28,14,21,42} }, // 16 in
            { {17,9,13,26},  {19,10,14,29}, {21,10,15,31}, {23,11,17,34}, {24,12,18,36}, {25,13,19,38}, {27,14,20,41}, {29,14,21,43}, {31,15,23,46} }, // 18 in
            { {20,10,15,30}, {22,11,16,33}, {23,12,17,35}, {25,13,19,38}, {27,13,20,40}, {28,14,21,42}, {30,15,22,45}, {31,16,23,47}, {33,17,25,50} }, // 20 in
            { {23,11,17,34}, {25,12,18,37}, {26,13,19,39}, {28,14,21,42}, {29,15,22,44}, {31,15,23,46}, {33,16,24,49}, {34,17,25,51}, {36,18,27,54} }  // 22 in
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView briquettesAbove = getView().findViewById(R.id.num_briquettes_above);
        final TextView briquettesBelow = getView().findViewById(R.id.num_briquettes_below);
        final TextView briquettesTotal = getView().findViewById(R.id.num_briquettes_total);
        final ImageView methodImage = getView().findViewById(R.id.method_image);

        Resources res = getResources();

        sizeWheel = getView().findViewById(R.id.size_wheel);
        ArrayWheelAdapter<String> adapter1 =
                new ArrayWheelAdapter<>(getContext(), res.getStringArray(R.array.size_inches_array));
        adapter1.setTextSize(18);
        sizeWheel.setViewAdapter(adapter1);
        sizeWheel.setCurrentItem(0);
        sizeWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (!scrolling) {
                    computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
                }
            }
        });
        sizeWheel.addScrollingListener( new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            @Override
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
            }
        });

        temperatureWheel = getView().findViewById(R.id.temperature_wheel);
        ArrayWheelAdapter<String> adapter2 =
                new ArrayWheelAdapter<>(getContext(), res.getStringArray(R.array.temperature_farenheit_array));
        adapter2.setTextSize(18);
        temperatureWheel.setViewAdapter(adapter2);
        temperatureWheel.setCurrentItem(0);
        temperatureWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (!scrolling) {
                    computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
                }
            }
        });
        temperatureWheel.addScrollingListener( new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            @Override
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
            }
        });

        methodWheel = getView().findViewById(R.id.cook_style_wheel);
        ArrayWheelAdapter<String> adapter3 =
                new ArrayWheelAdapter<>(getContext(), res.getStringArray(R.array.cook_style_array));
        adapter3.setTextSize(18);
        methodWheel.setViewAdapter(adapter3);
        methodWheel.setCurrentItem(0);
        methodWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (!scrolling) {
                    computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
                    setMethodImage(methodImage, methodWheel.getCurrentItem());
                }
            }
        });
        methodWheel.addScrollingListener( new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            @Override
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
                setMethodImage(methodImage, methodWheel.getCurrentItem());
            }
        });
        computeBriquettes(briquettesAbove, briquettesBelow, briquettesTotal);
        setMethodImage(methodImage, methodWheel.getCurrentItem());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dutch_oven, container, false);
    }

    private void computeBriquettes(TextView briquettesAbove, TextView briquettesBelow, TextView briquettesTotal) {

        int sizeIndex = sizeWheel.getCurrentItem();
        int temperatureIndex = temperatureWheel.getCurrentItem();
        int methodIndex = methodWheel.getCurrentItem();

        int numTop = BRIQUETTES_ABOVE[sizeIndex][temperatureIndex][methodIndex];
        int numBottom = BRIQUETTES_BELOW[sizeIndex][temperatureIndex][methodIndex];
        int numTotal = numTop + numBottom;

        briquettesAbove.setText(String.valueOf(numTop));
        briquettesBelow.setText(String.valueOf(numBottom));
        briquettesTotal.setText(String.valueOf(numTotal));
    }

    private void setMethodImage(ImageView methodImage, int selMethod) {

        switch (selMethod) {
            case 0:
                methodImage.setImageResource(R.drawable.method1);
                return;
            case 1:
                methodImage.setImageResource(R.drawable.method2);
                return;
            case 2:
                methodImage.setImageResource(R.drawable.method3);
                return;
            case 3:
                methodImage.setImageResource(R.drawable.method4);
                break;
        }
    }
}
