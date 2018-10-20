package method.mmdo.little.initializers;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ComponentFactory {
    public static LinearLayout createLinearLayout(Context context) {
        return new LinearLayout(context);
    }

    public static EditText createEditText(Context context) {
        return new EditText(context);
    }

    public static TextView createTextView(Context context) {
        return new TextView(context);
    }
}
