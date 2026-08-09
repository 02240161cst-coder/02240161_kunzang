package com.cstlab.myapplicationstudent


import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Main layout
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(40, 30, 40, 30)

        // Make screen scrollable
        val scrollView = ScrollView(this)
        scrollView.addView(layout)

        // Title
        val title = TextView(this)
        title.text = "Semester Registration"
        title.textSize = 26f
        title.setTextColor(Color.rgb(21, 101, 192))
        title.gravity = Gravity.CENTER
        title.setTypeface(null, android.graphics.Typeface.BOLD)

        layout.addView(title)

        addSpace(layout, 25)

        // 1. Name
        addLabel(layout, "Name")

        val name = EditText(this)
        name.hint = "Enter your name"
        layout.addView(name)

        addSpace(layout, 15)

        // 2. Student Number
        addLabel(layout, "Student No.")

        val studentNo = EditText(this)
        studentNo.hint = "Enter student number"
        layout.addView(studentNo)

        addSpace(layout, 15)

        // 3. Year
        addLabel(layout, "Year")

        val yearSpinner = Spinner(this)

        val years = arrayOf(
            "Select Year",
            "Year 1",
            "Year 2",
            "Year 3",
            "Year 4"
        )

        yearSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            years
        )

        layout.addView(yearSpinner)

        addSpace(layout, 15)

        // 4. Semester
        addLabel(layout, "Semester")

        val semesterGroup = RadioGroup(this)
        semesterGroup.orientation = RadioGroup.HORIZONTAL

        val autumn = RadioButton(this)
        autumn.text = "Autumn"

        val spring = RadioButton(this)
        spring.text = "Spring"

        semesterGroup.addView(autumn)
        semesterGroup.addView(spring)

        layout.addView(semesterGroup)

        addSpace(layout, 15)

        // 5. Registration Type
        addLabel(layout, "Registration Type")

        val typeGroup = RadioGroup(this)
        typeGroup.orientation = RadioGroup.VERTICAL

        val regular = RadioButton(this)
        regular.text = "Regular"

        val selfFinance = RadioButton(this)
        selfFinance.text = "Self Finance"

        val repeater = RadioButton(this)
        repeater.text = "Repeater"

        typeGroup.addView(regular)
        typeGroup.addView(selfFinance)
        typeGroup.addView(repeater)

        layout.addView(typeGroup)

        addSpace(layout, 15)

        // Modules for repeater
        addLabel(layout, "Modules being taken (Repeater)")

        val modules = EditText(this)
        modules.hint = "Enter modules"
        modules.minLines = 3
        modules.gravity = Gravity.TOP
        layout.addView(modules)

        addSpace(layout, 25)

        // Submit button
        val submit = Button(this)
        submit.text = "SUBMIT"
        submit.setTextColor(Color.WHITE)
        submit.setBackgroundColor(Color.rgb(33, 100, 200))
        layout.addView(submit)

        addSpace(layout, 10)

// Reset button
        val reset = Button(this)
        reset.text = "RESET"
        reset.setTextColor(Color.WHITE)
        reset.setBackgroundColor(Color.rgb(33, 100, 200))
        layout.addView(reset)

        addSpace(layout, 10)

// Cancel button
        val cancel = Button(this)
        cancel.text = "CANCEL"
        cancel.setTextColor(Color.WHITE)
        cancel.setBackgroundColor(Color.rgb(33, 100, 200))
        layout.addView(cancel)

        // Display the layout
        setContentView(scrollView)
    }

    // Function to add labels
    private fun addLabel(layout: LinearLayout, text: String) {
        val label = TextView(this)
        label.text = text
        label.textSize = 17f
        label.setTypeface(null, android.graphics.Typeface.BOLD)
        layout.addView(label)
    }

    // Function to add spacing
    private fun addSpace(layout: LinearLayout, size: Int) {
        val space = Space(this)

        layout.addView(
            space,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                size
            )
        )
    }
}