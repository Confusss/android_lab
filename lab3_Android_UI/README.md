# Lab_03   Android界面组件实验

### 1.项目的基本结构

![1](https://user-images.githubusercontent.com/106793045/197791597-34965342-b5d3-4c14-9496-67da7092e73d.jpg)

- MainActivity类是 “Android ListView的用法”实验的启动类
- AlertDialogActivity类是”创建自定义布局的AlertDialog“实验的启动类
- MenuActivity类是”使用XML定义菜单“实验的启动类
- ActionModeActivity类是"ActionMode的上下文菜单"的启动类

  



### 2. Android ListView的用法

- #### 思路

  ①layout文件中编写单元布局文件,且java文件中利用SimpleAdapter绑定组件

  ②用Toast.makeText创建Toast，设置基本属性并显示

- #### ①部分代码：

  ```java
  
  
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int i=0;i<parent.getCount();i++){

                    View item=parent.getChildAt(i);

                    if (position == i) {
                        item.setBackgroundResource(R.color.red);
                    } else {
                        item.setBackgroundResource(R.color.shallowblue);
                    }
                }
                Toast toast = Toast.makeText(MainActivity.this, names[position],Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 1150);
                toast.show();
            }
     
  
  		……………………………………
  ```

  ```xml
  
      <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                  android:orientation="vertical"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent"

                >


                  <ListView android:id="@+id/mylist"
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:background="@color/black"
            />

      </LinearLayout>
  ```


- #### 实验结果如下图：

- ![1](img\animals.png)

### 3.创建自定义布局的AlertDialog

- #### 思路：

  ①layout文件中编写文件作为AlertDialog的布局文件

  ②通过builder设置builder.setView，通过builder.show()进行显示

- #### ①部分代码：

  ```xml
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:layout_gravity="center_vertical"
    android:background="@color/newwhite"
    >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:gravity="center_vertical"
        android:orientation="horizontal"
        >

        <TextView
            android:id="@+id/name"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:paddingLeft="10dp"
            android:text="12312312"
            android:textColor="@color/black"
            android:textSize="20dp" />
        <ImageView
            android:id="@+id/header"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:gravity="right"
            android:layout_marginRight="10dp"/>
    </LinearLayout>
  					…………………………
  ```
  


  ```java
     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setView(View.inflate(this, R.layout.alert, null));
     builder.show();
  ```

- #### 实验结果如下图：

- ![2](img\register.png)

### 4.使用XML定义菜单

- #### 思路：

  ①创建xml文件并编写菜单的布局

  ②在MenuActivity中设置菜单并做出相关的绑定操作

- #### ①部分代码：

  ```xml
          <menu xmlns:android="http://schemas.android.com/apk/res/android">

              <item android:title="字体大小">
                  <menu >
                      <item
                          android:id="@+id/font_small"
                          android:title="小" />
                      <item
                          android:id="@+id/font_mid"
                          android:title="中" />
                      <item
                          android:id="@+id/font_big"
                          android:title="大" />
                  </menu>
              </item>
              <item
                  android:id="@+id/mi_normal"
                  android:title="Toast菜单项" />
              <item android:title="字体颜色" >
                  <menu >
                      <item
                          android:id="@+id/font_red"
                          android:title="红色" />
                      <item
                          android:id="@+id/font_black"
                          android:title="黑色" />
                  </menu>
              </item>
          </menu>
  ```
  


  ```java
    public boolean ItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.font_small:
                smallFont();
                break;
            case R.id.font_mid:
                midFont();
                break;
            case R.id.font_big:
                bigFont();
                break;
            case R.id.toast:
                toast();
                break;
            case R.id.font_red:
                red();
                break;
            case R.id.font_black:
                black();
                break;
        }
        return super.ItemSelected(item);
    }
  ```

- #### 实验结果如下图：

- ![2](img\text.png)

### 5.ActionMode的上下文菜单

- #### 思路：

  ①创建菜单样式的xml布局文件，java文件中使用simpleAdapter创建List用于绑定item

  ②用startActionMode()启用ActionMode

- #### 部分代码：
- 
  ```java
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionmode);

        List<Map<String, Object>> listItems =
                new ArrayList<>();
        for (int i = 0; i < names.length; i++)
        {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("Name", names[i]);
            selected_posistion.add(false);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.simple_item_1,
                new String[] { "Name"},
                new int[] { R.id.name});
        ListView list = (ListView) findViewById(R.id.actionModeList);

        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          if(actionMode==null) actionMode=startActionMode(actionModeCallback);

          selected_posistion.set(position,!selected_posistion.get(position));

                if(selected_posistion.get(position)){
                        view.setBackgroundResource(R.color.skyblue);
              flag++;
                    } else {
              view.setBackgroundResource(R.color.shallowblue);
              flag--;
                    }

                actionModeCallback.onActionItemClicked(actionMode,null);
            }
        });

    }
      
  ```



```xml
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@+id/actionModeList"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></ListView>
    </LinearLayout>
```

- #### 实验结果如下图：

- ![2](img\select.png)

