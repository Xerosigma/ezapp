# ezapp
A starting point for Android apps looking to use Material Design.

**Supports Android SDK version 14+**


CollapsingToolbarLayout
================================
You can add content to the CollapsingToolbarLayout by accessing the content container:
"ez_CollapsingToolbarContentLayout"

```
final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.ez_CollapsingToolbarContentLayout);
getLayoutInflater().inflate(R.layout.parallax_image_view, viewGroup);
```


You may need to set "layout_collapseMode" to "parallax" and "fitsSystemWindows" to true to get the parallax effect when the toolbar collapses.

```
<ImageView
    app:layout_collapseMode="parallax"
    android:fitsSystemWindows="true"
    android:id="@+id/ez_CollapsingToolbarImage"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop"
    android:src="@drawable/ic_avatars"/>
```
