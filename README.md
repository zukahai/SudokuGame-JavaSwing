## <p align="center"> Làm Game Sudokui Bằng Java </p>
<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Sudoku/1.png" alt="Tieude" /> </p>

## Lập Trình Game Sudoku Bằng Java

Trò chơi sudoku đòi hỏi ta sự nhanh nhạy nhưng vẫn phải đảm bảo tính chính xác và cẩn thận. Hôm nay minh sẽ hướng dẫn các bạn lập trình game sudoku nhé.

### Chơi sudoku thế nào?

Ban đầu có tên gọi là Number Place là một trò chơi câu đố sắp xếp chữ số dựa trên logic theo tổ hợp. Mục tiêu của trò chơi là điền các chữ số vào một lưới 9×9 sao cho mỗi cột, mỗi hàng, và mỗi phần trong số chín lưới con 3×3 cấu tạo nên lưới chính (cũng gọi là "hộp", "khối", hoặc "vùng") đều chứa tất cả các chữ số từ 1 tới 9. Câu đố đã được hoàn thành một phần, người chơi phải giải tiếp bằng việc điền số. Mỗi câu đố được thiết lập tốt có một cách làm duy nhất. Những bảng trò chơi đã được hoàn chỉnh luôn tạo thành một loại ma trận hình vuông Latinh với một điều kiện hạn chế được bổ sung về nội dung của từng khu vực lưới con. Ví dụ, mỗi số nguyên duy nhất sẽ có thể không xuất hiện hai lần trong cùng một hàng, cột hoặc bất kỳ một trong chín tiểu khu/lưới con 3×3 nào của bảng trò chơi 9x9.

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Sudoku/2.png" alt="Tieude" /> </p>

### Ý tưởng lập trình

#### 1. Tạo dữ liệu cho trò chơi

Dữ liệu chính trò chơi này là một ma trận kích thước 9x9, cụ thể là một dãy 81 số từ 1 đến 9 sao cho mỗi hàng một cột và mỗi ô vuông đều gồm các số từ 1 đến 9 và không có bất kỳ 2 số nào khác nhau. Lúc đầu mình nghĩ đến phương án dùng backtracking (đệ quy quay lui) để tạo ra các trường hợp của bảng sudoku nhưng sau đó mình đã nhận ra là có rất nhiều cách, lúc tạo như vậy sẽ rất là mất thời gian nên mình đã tạo ra các bảng sudoku trước, sau đó đưa thẳng trước tiếp vào code và chọn random một trường hợp làm dữ liệu cho bảng sudoku của mình.

#### 2. Các yếu tố khác của trò chơi

Để tăng phần thú vị cho trò chơi, mình sẽ chia trò chơi thành 3 mức độ khác nhau (dễ, trung bình và khó), mức độ càng tăng thì số ô bị ẩn (số lượng ô mà người chơi cần phải điền vào) càng nhiều.

Người chơi sẽ chiến thắng khi điền hết tất cả các ô vuông sao cho vẫn thỏa mãn quy tắc của trò chơi.

Nên có thêm phần tính thời gian và phần bảng xếp hạng để trò chơi thêm phần tú vị.

### Bắt đầu lập trình

#### 1. Thiết lập giao diện

Giao diện chính của trò chơi mà một ô vuông lớn bao gồm 81 ô vuông nhỏ và chia thành 9 hàng và 9 cột, bên cạnh đó cũng chia thành 9 ô vuông nhỏ hơn và mỗi ô vuông cũng chưa 9 ô vuông nhỏ gồm 3 hàng và 3 cột, như hình dưới:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Sudoku/3.png" alt="Tieude" /> </p>

Để làm được giao diện như thế này, ta chỉ cần tạo ra ma trận JButton[9][9], và cần setBorfer cho từng button để có được giao diện như trên.

```Java
	public Container init() {
		Container cn = this.getContentPane();
		pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());
		time_lb = new JLabel("00:00:00:00");
		sls_lb = new JLabel("Có thể sai 3 lần");
		
		newGame_bt = new JButton("New Game");
		newGame_bt.addActionListener(this);
		highScore_bt = new JButton("High Score");
		highScore_bt.addActionListener(this);
		
		lv.addItem("Dễ");
		lv.addItem("Trung bình");
		lv.addItem("Khó");
		lv.setSelectedIndex(LV);
		
		
		pn2.add(newGame_bt);
		pn2.add(lv);
		pn2.add(time_lb);
		pn2.add(sls_lb);
		pn2.add(highScore_bt);
		cn.add(pn2, "North");
		
		pn = new JPanel();
		pn.setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				bt[i][j].addKeyListener(this);
				bt[i][j].setActionCommand(i + " " + j);
				bt[i][j].setBackground(Color.white);
				bt[i][j].setFont(new Font("UTM Micra", 1, 30));
				bt[i][j].setForeground(Color.black);
				pn.add(bt[i][j]);
			}
		for (int i = 0; i < 9; i += 3)
			for (int j = 0; j < 9; j += 3) {
				bt[i][j].setBorder(BorderFactory.createMatteBorder(3,3,1,1, Color.black));
				bt[i][j + 2].setBorder(BorderFactory.createMatteBorder(3,1,1,3, Color.black));
				bt[i + 2][j + 2].setBorder(BorderFactory.createMatteBorder(1,1,3,3, Color.black));
				bt[i + 2][j].setBorder(BorderFactory.createMatteBorder(1,3,3,1, Color.black));
				bt[i][j + 1].setBorder(BorderFactory.createMatteBorder(3,1,1,1, Color.black));
				bt[i + 1][j + 2].setBorder(BorderFactory.createMatteBorder(1,1,1,3, Color.black));
				bt[i + 2][j + 1].setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.black));
				bt[i + 1][j].setBorder(BorderFactory.createMatteBorder(1,3,1,1, Color.black));
				bt[i + 1][j + 1].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
			}
		
		cn.add(pn);
		this.setVisible(true);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		try {
			creatMatrix();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time_lb.setText(next(time_lb));
			}
		});
		return cn;
	}
```

#### 2. Đọc dữ liệu và ẩn đi một số ô số

Để đọc dữ liệu thì rất đơn giản, ta chỉ cần chọn ra một dòng dữ liệu (là một chuỗi gồm 81 ký tự từ '1' đến '9')  random mà mình đã tạo, sau khi tạo xong chúng ta cần phải ẩn đi một số lượng ô số chính là các ô mà người chơi cần điền vào, mức độ càng khó thì càng cần ẩn nhiều ô số.

```Java
	public void creatMatrix() throws FileNotFoundException {
		str = S[(int)((S.length - 1) * Math.random()) + 1];
        int N = 0;
        for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		a[i][j] = A[i][j] = str.charAt(i * 9 + j) - 48;
        
        for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j ++) {
				x[N] = i;
				y[N] = j;
				temp[N++] = (int) (10000 * Math.random());
			}
		for (int i = 0; i < N - 1; i ++)
			for (int j = i + 1; j < N; j++)
				if (temp[i] > temp[j]) {
					int t = x[i];
					x[i] = x[j];
					x[j] = t;
					
					t = y[i];
					y[i] = y[j];
					y[j] = t;
					
					t = temp[i];
					temp[i] = temp[j];
					temp[j] = t;
				}
		for (int i = 0; i < an[LV]; i++) {
			a[x[i]][y[i]] = 0;
		}
		for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		if (a[i][j] > 0)
        			bt[i][j].setText(a[i][j] + "");
		for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		aa[i][j] = a[i][j];
    }
```

### Các phần xử lý trong trò chơi

#### 1. Lúc click chuột vào một ô vuông

Lúc click chuột vào một ô vuông trong bảng sudoku thì đầu tiên ta sẽ lưu là ví trị (hàng và cột của ô vưa click). Nếu ô vừa click đã được điền một ô số thì ta sẽ thay đổi backgroundColor của các ô chứa ô số đó:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/Sudoku/4.png" alt="Tieude" /> </p>

Ngược lại thì vẫn lưu lại vị trí của ô đó và chờ người dùng bấm các phím từ 1 đến 9 để điền số vào ô trống đó

```Java
      String s = e.getActionCommand();
			int k = s.indexOf(32);
			int i = Integer.parseInt(s.substring(0, k));
			int j = Integer.parseInt(s.substring(k + 1, s.length()));
			I = i;
			J = j;
			if (a[I][J] > 0) {
				for (i = 0; i < 9; i++)
					for (j = 0; j < 9; j++)
						if (a[i][j] == a[I][J])
							bt[i][j].setBackground(Color.gray);
			}
```

#### 2. Lúc điền các số vào ô trống

Ta chỉ quan tâm khi người dùng bấm vào các phím từ 1 đến 9 ( có getKeyCode từ 49 đến 57 hoặc từ 97 đến 105), nếu ô vuông đó chưa được điền thì ta tiến hành điền nó vào ô trống, nếu điền sai thì thông báo cho người chơi (Nếu sai quá nhiều lần thì có thể xử thua cho người chơi), nếu điền đúng và đó là ô cuối cùng cần điền thì thông báo người chơi đã chiến thắng:

```Java
	public void keyPressed(KeyEvent e) {
		int v = e.getKeyCode();
		if ((v >= 49 && v <= 57) || (v >= 97 && v <= 105)) {
			if (v >= 49 && v <= 57)
				v -= 48;
			if (v >= 97 && v <= 105)
				v -= 96;
			if (aa[I][J] == 0) {
				bt[I][J].setText(v + "");
				if (v == A[I][J]) {
					a[I][J] = v;
					bt[I][J].setForeground(Color.BLUE);
					boolean check = true;
					for (int i = 0; i <  9; i++)
						for (int j = 0; j < 9; j++)
							if (a[i][j] != A[i][j])
								check = false;
					if (check) {
						// nguoi choi do thang
					}
				}
				else {
					// nguoi choi da chon sai
				}
			}
		}
		
	}
```

### Kết

Trên đây là các phần cơ bản của bài viết hướng dẫn lập trình trò chơi sudoku của mình, mình còn một số tính năng chưa làm được như phần ghi chú trong trò chơi, các bạn có thể phát triển thêm phần này.

## <p align="center">  :tv: Thanks for whatching :earth_africa: </p>
