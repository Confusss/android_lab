# lab_02 Android 界面布局

### 1.项目的基本结构

![1](https://user-images.githubusercontent.com/106793045/194738296-25f21b4e-6f80-4630-9c26-d1298f8840bf.jpg)





  

### 2. 线性布局实验



- #### 部分实验代码：

  ```xml
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:background="@color/black"
      android:orientation="vertical"
      android:paddingLeft="4dp"
      android:paddingTop="5dp"
      android:paddingRight="4dp">
      <LinearLayout
          android:layout_width="match_parent"
          android:layout_height="50dp"
          android:orientation="horizontal">
          <TextView
              android:layout_width="0dp"
              android:layout_height="50dp"
              android:layout_weight="1"
              android:background="@drawable/border"
              android:gravity="center"
              android:text="One,One"
              android:textColor="#7E7E7E"
              android:textSize="12dp" />
                      ……………………………………
  ```

- #### 实验结果如下图：

- ![2](https://github.com/Confusss/android_lab/blob/master/lab2_layout/result_pic/4x4_table.png)



### 3.表格布局实验



- #### 部分实验代码：

  ```xml
  <TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="match_parent"
      android:layout_height="match_parent">
      <TableRow
          android:layout_width="match_parent"
          android:layout_height="match_parent">
          <TextView
              android:id="@+id/textView1"
              android:layout_width="246dp"
              android:layout_height="wrap_content"
              android:paddingLeft="25dp"
              android:text="open..."
              android:textSize="20sp" />
  
          <TextView
              android:id="@+id/textView2"
              android:layout_width="83dp"
              android:layout_height="match_parent"
              android:gravity="right"
              android:text="Ctrl-O"
              android:textSize="20sp" />
      </TableRow>
                       ……………………………………
  ```
  
- #### 实验结果如下图：

- ![3](https://github.com/Confusss/android_lab/blob/master/lab2_layout/result_pic/CMD.png)



### 4.约束布局1实验


- #### 部分实验代码：

```xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFFF">
    
    <Button
        android:id="@+id/button19"
        android:layout_width="80dp"
        android:layout_height="48dp"
        android:layout_marginStart="8dp"
        android:backgroundTint="#E8E3E3"
        android:text="="
        android:textColor="#000000"
        app:layout_constraintStart_toStartOf="@+id/guideline11"
        app:layout_constraintTop_toTopOf="@+id/button18" />
    <Button
        android:id="@+id/button17"
        android:layout_width="80dp"
        android:layout_height="48dp"
        android:layout_marginEnd="16dp"
        android:backgroundTint="#E8E3E3"
        android:text="."
        android:textColor="#000000"
        app:layout_constraintEnd_toStartOf="@+id/button18"
        app:layout_constraintTop_toTopOf="@+id/button18" />
    <Button
        android:id="@+id/button18"
        android:layout_width="85dp"
        android:layout_height="48dp"
        android:layout_marginEnd="8dp"
        android:backgroundTint="#E8E3E3"
        android:text="0"
        android:textColor="#000000"
        app:layout_constraintEnd_toStartOf="@+id/guideline11"
        app:layout_constraintTop_toTopOf="@+id/guideline15" />

    <Button
        android:id="@+id/button20"
        android:layout_width="80dp"
        android:layout_height="48dp"
        android:layout_marginStart="16dp"
        android:backgroundTint="#E8E3E3"
        android:text="-"
        android:textColor="#000000"
        app:layout_constraintStart_toEndOf="@+id/button19"
        app:layout_constraintTop_toTopOf="@+id/button18" />
    
        							 ……………………………………
```

- #### 实验结果如下图:

- ![4](https://github.com/Confusss/android_lab/blob/master/lab2_layout/result_pic/calculate.png)



### 4.约束布局2实验



- #### 部分实验代码：

  ```xml
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:background="#FFFFFF"
      android:backgroundTint="#FFFFFF"
      tools:layout_editor_absoluteX="9dp"
      tools:layout_editor_absoluteY="14dp">
      <TextView
          android:id="@+id/textView12"
          android:layout_width="113dp"
          android:layout_height="77dp"
          android:layout_marginStart="12dp"
          android:background="#009688"
          android:gravity="center"
          android:text="MARS"
          android:textColor="#FFFFFF"
          app:layout_constraintBottom_toTopOf="@+id/guideline2"
          app:layout_constraintStart_toStartOf="@+id/guideline7"
          app:layout_constraintTop_toTopOf="@+id/guideline16"
          app:layout_constraintVertical_bias="0.0" />
      <TextView
          android:id="@+id/textView7"
          android:layout_width="119dp"
          android:layout_height="77dp"
          android:layout_marginEnd="12dp"
          android:background="#009688"
          android:gravity="center"
          android:text="DCA"
          android:textColor="#FFFFFF"
          app:layout_constraintBottom_toTopOf="@+id/guideline2"
          app:layout_constraintEnd_toStartOf="@+id/guideline7"
          app:layout_constraintTop_toTopOf="@+id/guideline16"
          app:layout_constraintVertical_bias="0.0" />
  
      <ImageView
          android:id="@+id/imageView2"
          android:layout_width="60dp"
          android:layout_height="52dp"
          android:layout_marginTop="13dp"
          android:src="@drawable/double_arrows"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.498"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="@+id/guideline16" />
  
      <ImageView
          android:id="@+id/imageView3"
          android:layout_width="93dp"
          android:layout_height="81dp"
          android:layout_marginEnd="156dp"
          android:src="@drawable/galaxy"
          app:layout_constraintBottom_toTopOf="@+id/button2"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintTop_toTopOf="@+id/guideline2"
          app:layout_constraintVertical_bias="0.557" />
  
      <ImageView
          android:id="@+id/imageView4"
          android:layout_width="39dp"
          android:layout_height="29dp"
          android:layout_marginEnd="24dp"
          android:src="@drawable/rocket_icon"
          app:layout_constraintBottom_toTopOf="@+id/button2"
          app:layout_constraintEnd_toStartOf="@+id/imageView3"
          app:layout_constraintHorizontal_bias="1.0"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="@+id/guideline2"
          app:layout_constraintVertical_bias="0.551" />
  								 ……………………………………
  ```

- #### 实验结果如下图：

- ![5](https://github.com/Confusss/android_lab/blob/master/lab2_layout/result_pic/rocky.png)
