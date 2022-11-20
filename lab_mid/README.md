# NotePad
## 笔记的时间戳
### 关键代码

（1）在相关布局文件中创建“时间戳”的TextView

```xml
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:orientation="vertical" android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:paddingTop="5dp"
            android:paddingBottom="12dp"
            android:background="#F6F1C3"
            android:outlineAmbientShadowColor="@color/black">

          

            <TextView
                android:id="@+id/tv_time"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="时间"
                android:textSize="16dp"
                android:textColor="@color/greyC"/>

</LinearLayout>

```
（2）在创建笔记的onCreate的操作前提下，根据系统获取的当前时间，返回给“时间戳”板块并进行显示操作
```java
        //通过该函数返回当前时间
       public String dateToStr(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

        String time = cursor.getString(cursor.getColumnIndex(TIME));
        values.put(TIME, time);

        adapter = new NoteAdapter(getApplicationContext(), noteList);


        refreshListView();
        lv.setAdapter(adapter);

        

        public long dateStrToSec(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long secTime = format.parse(date).getTime();
        return secTime;
    }
```

### 实现效果

笔记的下方会显示保存笔记时的当前时间

![image](https://user-images.githubusercontent.com/94881561/202888794-74557c27-6a7d-4546-a3e8-6ffd98a42c1a.png)

## 按关键字搜索笔记
### 关键代码
（1） 在相关的xml文件中，加入提供搜索的显示控件，并设置该空间的一些属性
```xml
         <item
        android:id="@+id/action_search"
        android:icon="@android:drawable/ic_menu_search"
        android:iconTint="#E17272"
        android:title="Search"
        app:actionViewClass="androidx.appcompat.widget.SearchView"
        app:showAsAction="always" />
```
（2）使用关键字搜索笔记
```java
        public void open(){
        db = dbHandler.getWritableDatabase();
        }

     public Note getNote(long id){
        
        Cursor cursor = db.query(NoteDatabase.TABLE_NAME,columns,NoteDatabase.ID + "=?",
                new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Note e = new Note(cursor.getString(1),cursor.getString(2), cursor.getInt(3));
        return e;
    }

    //用于搜索的过滤器，继承了Filter
     class MyFilter extends Filter {
        
        @Override
        protected FilterResults performFiltering(CharSequence Sequence) {
            FilterResults result = new FilterResults();
            List<Note> list;
            if (TextUtils.isEmpty(Sequence)) {
                list = backList;
            } else {
                list = new ArrayList<>();
                for (Note note : backList) {
                    if (note.getContent().contains(Sequence)) {
                        list.add(note);
                    }

                }
            }
            result.values = list; //将得到的集合保存到FilterResults的value变量中
            
            return result;
        }
     }

```

### 实现效果

点击搜索的控件

![image](https://user-images.githubusercontent.com/94881561/202888790-198a2ddb-96cc-4c5d-bb8f-75da0b31b902.png)

输入搜索的关键字

![image](https://user-images.githubusercontent.com/94881561/202888789-123ee42e-53cb-47f6-9ea6-1764715ed1ca.png)


## 夜间模式（UI变化）
### 关键代码
（1）在xml文件中通过setting按钮，显示“夜间模式”的功能模块
```xml
<LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:orientation="vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="夜间模式"
                android:textColor="?attr/tvMainColor"
                android:textSize="20dp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="开启或关闭夜间模式"
                android:textColor="?attr/tvSubColor"
                android:textSize="16dp" />
        </LinearLayout>
```
（2）通过设置相应的函数监听点击事件，并且对该点击事件返回“夜间模式”的方法
```java
     public boolean isNightMode(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        return sharedPreferences.getBoolean("nightMode", false);
    }

    public void setNightMode(){
        if(isNightMode()) this.setTheme(R.style.NightTheme);
        else setTheme(R.style.DayTheme);

    }
    //设置相关页面的背景为“夜间模式”的主题
    public View getView(int position, View convertView, ViewGroup parent) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        mContext.setTheme((sharedPreferences.getBoolean("nightMode", false)? R.style.NightTheme: R.style.DayTheme));

        View v = View.inflate(mContext, R.layout.note_layout, null);
        TextView tv_content = (TextView)v.findViewById(R.id.tv_content);
        TextView tv_time = (TextView)v.findViewById(R.id.tv_time);

        return v;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.preference_layout);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Intent intent = getIntent();
        if(intent.getExtras() != null) night_change = intent.getBooleanExtra("night_change", false);
        else night_change = false;

        initView();
    }
    //实现变换夜间模式的参数调整
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.preference_layout);
        Preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        

        if(intent.getExtras() != null)
         night_change = intent.getBooleanExtra("night_change", false);
        else
         night_change = false;

        initView();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
   

        if(isNightMode()) 
        myToolbar.setNavigationIcon(getDrawable(R.drawable.ic_settings_white_24dp));
        else 
        myToolbar.setNavigationIcon(getDrawable(R.drawable.ic_settings_black_24dp));
    }
```


### 实现效果

![image](https://user-images.githubusercontent.com/94881561/202888787-88224263-2455-4f33-af8b-8e6216845576.png)

点击右边的按钮进入夜间模式

![image](https://user-images.githubusercontent.com/94881561/202888788-e1800570-b4ed-4e67-b190-4f97bf798052.png)


## 笔记分类标签及添加标签
### 关键代码
（1）在xml文件中设置用于显示标签和设置标签的控件
```xml
        //侧面导航栏显示出的标签控件和设置标签的控件
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="48dp"
            android:orientation="horizontal"
            android:background="?attr/tvBackground">
            <ImageView
                android:layout_width="44dp"
                android:layout_height="match_parent"
                android:src="?attr/addTag"/>
            <TextView
                android:id="@+id/add_tag"
                android:clickable="true"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:text="添加新的tag"
                android:gravity="center_vertical"
                android:textColor="?attr/tvMainColor"
                android:textSize="24dp"
                android:layout_marginStart="12dp"/>
        </LinearLayout>
        //主页显示出的用于选择标签的控件
         <Spinner
            android:id="@+id/spinner"
            style="@style/spinner"
            android:layout_width="80dp"
            android:layout_height="wrap_content"
            android:layout_marginLeft="230dp"
            android:dropDownWidth="wrap_content"
            android:popupTheme="@style/spinnerPop"
            android:spinnerMode="dropdown" />
```
（2）在相关java文件内中，编写能够控制笔记标签的方法
```java
    //获取标签
    public TagAdapter(Context context, List<String> tagList, List<Integer> numList) {
        this.context = context;
        this.tagList = tagList;
        this.numList = numList;

    }
    //添加新的标签后标签数加一
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tag = (int)id + 1;
                tagChange = true;
            }

    //选择标签
     public void onClick(DialogInterface dialog, int which) {
            List<String> tagList = Arrays.asList(sharedPreferences.getString("tagListString", null).split("_")); //获取tags

                  String name = et.getText().toString();
                 if (!tagList.contains(name)) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String oldTagListString = sharedPreferences.getString("tagListString", null);
                      String newTagListString = oldTagListString + "_" + name;
                         SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tagListString", newTagListString);
                         editor.commit();
                           refreshTagList();
                             }
                       else Toast.makeText(context, "Repeated tag!", Toast.LENGTH_SHORT).show();
                      }
                         .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener())
    
    //增加tag后对tag总列表进行更新操作
    private void refreshTagList() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        List<String> tagList = Arrays.asList(sharedPreferences.getString("tagListString", null).split("_")); //获取tags
        tagAdapter = new TagAdapter(context, tagList, numOfTagNotes(tagList));
        lv_tag.setAdapter(tagAdapter);
        tagAdapter.notifyDataSetChanged();
    }
```
### 实现效果

这是原来的tag列表

![image](https://user-images.githubusercontent.com/94881561/202888792-1d44e461-3f99-452e-93bd-60bb169fe805.png)


添加新的标签的弹窗

![image](https://user-images.githubusercontent.com/94881561/202888780-3394ec6a-a3cd-4770-b29e-0c6094cdef12.png)

添加新标签后的tag列表

![image](https://user-images.githubusercontent.com/94881561/202888785-5bd00228-84cc-4a22-962e-21374ad7f8be.png)

选择新标签后主页的反应

![image](https://user-images.githubusercontent.com/94881561/202888786-d99d050c-b7ec-4c15-9f21-4b0fa0347afd.png)


