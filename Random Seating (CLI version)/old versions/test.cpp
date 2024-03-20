//updated on 21/08/14
//compile option -std=c++11 required in windows
#include<iostream>
#include<string.h>
#include<cstdio>
#include<chrono>
#include<random>
#include<map>
using namespace std;
#define pr pair<int,int> 
#define mp make_pair
long long a;
mt19937_64 rng(chrono::steady_clock::now().time_since_epoch().count());
//printf("%llu\n", rng());
bool book[31];
string name1[16];

int num;

void init() {
    a=0;
    memset(book,0,sizeof(book));
    num=0;
    idM=idT=idF=idH=idStanley=idCarolyn=idViola=0;
}
int Coord2Int(pr coord) {
    if(coord.first==6) return 31;
    return coord.first*5+coord.second;
}
pr Int2Coord(int coord) {
    if(coord==30) return mp(6,2);
    return mp(coord/5,coord%5);
}
bool isAdj(pr p1, pr p2) {
    if(abs(p1.first-p2.first)<=1 && abs(p1.second-p2.second)<=1) 
        return 1;
    return 0;
}
bool isAdj(int c1, int c2) {
    pr p1=Int2Coord(c1), p2=Int2Coord(c2);
    return isAdj(p1,p2);
}
int dis(int x1, int y1, int x2, int y2) {
    int ans=0;
    if(y1>y2) swap(y1,y2);
    if(y1<=0 && 1<=y2) ans++;
    if(y1<=2 && 3<=y2) ans++;
    return 2*(abs(x1-x2)+abs(y1-y2))+ans;
}
int getVal(int x, int y) {
    int ans=0,disSq=0;
    for(int i=0;i<6;i++) 
        for(int j=0;j<5;j++) {
            if(x==i && y==j) continue;
            disSq=(22-dis(i,j,x,y))*(22-dis(i,j,x,y));
            if(Coord2Int(mp(i,j))<16) 
                ans+=disSq*Mp[name1[Coord2Int(mp(i,j))]];
            else ans+=disSq*Mp[name2[Coord2Int(mp(i,j))-16]];
        }
    disSq=(22-dis(x,y,6,2))*(22-dis(x,y,6,2));
    ans+=disSq*Mp[name2[14]];
    return ans;
}
int main() {
    //读入
    READ:
    init();
    for(int i=0;i<32;i++) 
        cin>>name[i];

    memset(book,0,sizeof(book));
    for(int i=0;i<32;i++) {
        num=rng()%32;
        if(book[num]==0) //用 set/map 代替
            book[num]=1, tmp[i]=name1[num];
        else i--;
    }

    if(isAdj(idM,idF))
        goto READ;

    //輸出部分
    return 0;
}
