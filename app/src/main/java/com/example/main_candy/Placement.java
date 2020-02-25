package com.example.main_candy;

import android.os.SystemClock;

import static java.lang.Math.random;

public class Placement extends Block {
    int[] picture = {R.drawable.amber32, R.drawable.ame, R.drawable.rect32, R.drawable.romb32, R.drawable.sapphire32, R.drawable.tri32, R.drawable.blank};
    Block[][] blocks = new Block[9][9];
    int[][] blockLink = new int [9][9]; //가로연결체크에 사용
    int[][] blockLink2 = new int [9][9]; //세로연결체크에 사용

    Placement() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blocks[i][j] = new Block();


            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { //블록끼리 중복을 최소화하기위해
                if(getRightBlock(blocks,i,j)!=null)
                    if(blocks[i][j].getType()==blocks[i][j+1].getType())
                        blocks[i][j] = new Block();
                if(getDownBlock(blocks,i,j)!=null)
                    if(blocks[i][j].getType()==blocks[i+1][j].getType())
                        blocks[i][j] = new Block();

            }
        }
    }

    public int getBlockType(int x, int y) {
        return picture[blocks[x][y].getType()];
    }

    public void swapBlockType(int x, int y, int i, int j) {
        int a, b;
        a = blocks[x][y].getType();
        b = blocks[i][j].getType();
        blocks[x][y].setType(b);
        blocks[i][j].setType(a);
    }

    public void setBlockType(int x, int y, int n) {
        blocks[x][y].setType(n);
    }

    public Block getRightBlock(Block block[][],int x,int y){ //바로오른쪽블록가져오기
        if(y+1<9) {
            return block[x][y + 1];
        }
        return null;
    }
    public Block getDownBlock(Block block[][],int x,int y){ //바로아래블록가져오기
        if(x+1<9) {
            return block[x+1][y];
        }
        return null;
    }
    public boolean boomCheck(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(blocks[i][j].getType() ==6){
                    return true;
                }
            }
        }
        return false;
    }

    public Block upCheck(Block block[][],int x,int y){
        if(x-1>=0) {
            return block[x - 1][y];
        }
        return null;
    }


    public void blockDrop(){
        if(boomCheck()){
            int n,i,p;
            for(int j=0;j<9;j++){
                n=-1; //터진게없으면 -1
                p=0;
                for(i=0;i<9;i++){
                    if(blocks[i][j].getType() ==6) { //터진블록의 개수를 셈
                        n++;
                    }
                }
                if(n > -1) { //터진블록이 한개이상일때
                    for(i=8;i>n;i--){
                        if(blocks[i][j].getType()==6){ //빈공간이면
                            for(p=i-1;p>=0;p--){
                                if(blocks[p][j].getType()!=6){ //빈공간이아니면
                                    swapBlockType(i,j,p,j); //위치바꾸고 반복문끝냄
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public int[][] makeBlock(int [][]back){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(blocks[i][j].getType()==6){
                    blocks[i][j].setType((int)(random()*6));
                    back[i][j]=1;
                }
            }
        }

        return back;
    }




    public int explosionCheck() {
        int n,result=0;
        try {
            for (int i = 0; i < 9; i++) { //오른쪽체크
                for (int j = 0; j < 9; j++) {
                    blockLink[i][j] = 0;
                    n = 0;
                    while (getRightBlock(blocks, i, j + n) != null) {
                        if((getRightBlock(blocks,i,j+n).getType()==blocks[i][j].getType())&&blocks[i][j].getType()!=6){
                            blockLink[i][j]++;
                            n++;
                        }
                        else
                            break;
                    }
                }
            }
            for (int j = 0; j < 9; j++) { //아래체크
                for (int i = 0; i< 9; i++) {
                    blockLink2[i][j] = 0;
                    n = 0;
                    while (getDownBlock(blocks, i+n, j) != null) {
                        if((getDownBlock(blocks,i+n,j).getType()==blocks[i][j].getType())&&blocks[i][j].getType()!=6){
                            blockLink2[i][j]++;
                            n++;
                        }
                        else
                            break;
                    }
                }
            }
        }catch (Exception e){
            blocks[0][0].setType(6);
        }
        int p;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(blockLink[i][j]<2 && blockLink2[i][j]<2){ // 가로세로 둘다 이어지는게 2개이하
                    continue;
                }else if(blockLink[i][j]>=2 && blockLink2[i][j]>=2){ //가로세로 둘다 이어지는게 3개이상 (ㄱ,ㄴ자 연결일때)
                    blocks[i][j].setType(6); //가로세로교차점으로, 공통임
                    result++;
                    for(p=1;p<=blockLink[i][j];p++){
                        blocks[i][j+p].setType(6);
                        result++;
                    }
                    for(p=1;p<=blockLink2[i][j];p++){
                        blocks[i+p][j].setType(6);
                        result++;
                    }
                }
                else if(blockLink[i][j]>=2 && blockLink2[i][j]<2){ // 가로만 3개이상연결일때
                    for(p=0;p<=blockLink[i][j];p++){
                        blocks[i][j+p].setType(6);
                        result++;
                    }
                }else if(blockLink2[i][j]>=2 && blockLink[i][j]<2){ //세로만 3개이상 연결일때
                    for(p=0;p<=blockLink2[i][j];p++){
                        blocks[i+p][j].setType(6);
                        result++;
                    }
                }

            }
        }

        return result*60;
    }
}