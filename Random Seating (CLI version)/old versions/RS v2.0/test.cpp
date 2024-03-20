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
string name2[16];
string tmp[16];
int dx[8]={1,1,0,-1,-1,-1,0,1},dy[8]={0,1,1,1,0,-1,-1,-1};
map<string,int> Mp;
int num;
int idM,idT,idF,idH,idS,idC,idV;
void init() {
    a=0;
    memset(book,0,sizeof(book));
    //memset(name1,0,sizeof(name1));
    //memset(name2,0,sizeof(name2));
    //memset(tmp,0,sizeof(tmp));
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
    freopen("data.txt","r",stdin);
    for(int i=0;i<16;i++) 
        cin>>name1[i];
    for(int i=0;i<16;i++)
        cin>>name2[i];
    fclose(stdin);
    //依次取16个名字
    memset(book,0,sizeof(book));
    for(int i=0;i<16;i++) {
        num=rng()%16;
        if(book[num]==0)
            book[num]=1, tmp[i]=name1[num];
        else i--;
    }
    memset(book,0,sizeof(book));
    for(int i=0;i<16;i++) {
        num=rng()%16;
        if(book[num]==0)
            book[num]=1, name1[i]=name2[num];
        else i--;
    }
    for(int i=0;i<16;i++)
        name2[i]=tmp[i];

//    swap(name1[rng()%16],name2[rng()%16]);
//    swap(name1[rng()%16],name2[rng()%16]);

    for(int i=0;i<16;i++) 
        if(name1[i]=="-------") swap(name1[i],name2[15]);
    for(int i=0;i<15;i++) 
        if(name2[i]=="-------") swap(name2[i],name2[15]);

    //找不邻座的人
    for(int i=0;i<16;i++) {
        if(name1[i]=="Max") idMax=i;
        else if(name1[i]=="Fred") idFred=i;
        else if(name1[i]=="Hadis") idHadis=i;
        else if(name1[i]=="Stanley") idStanley=i;
        else if(name1[i]=="Carolyn") idCarolyn=i;
        else if(name1[i]=="Viola") idViola=i;
        else if(name1[i]=="Tim") idTim=i;
    }
    for(int i=0;i<16;i++) {
        if(name2[i]=="Max") idMax=i+16;
        else if(name2[i]=="Fred") idFred=i+16;
        else if(name2[i]=="Hadis") idHadis=i+16;
        else if(name2[i]=="Stanley") idStanley=i+16;
        else if(name2[i]=="Carolyn") idCarolyn=i+16;
        else if(name2[i]=="Viola") idViola=i+16;
        else if(name2[i]=="Tim") idTim=i+16;
    }
    if(isAdj(idMax,idFred) || isAdj(idMax,idHadis) || isAdj(idFred,idHadis) 
        || isAdj(idMax,idCarolyn) || isAdj(idStanley,idViola) || isAdj(idTim,idViola))
        goto READ;

    //优化个人位置
    Mp["Jason"]=3;
    Mp["Yuki"]=4;
    Mp["Andrew"]=2;
    Mp["Eric"]=2;
    Mp["Smile"]=3;
    Mp["Dorothy"]=3;
    Mp["Beryl"]=4;
    Mp["Max"]=4;
    Mp["Lisa"]=4;
    Mp["Hadis"]=2;
    Mp["Kevin"]=2;
    Mp["Vivian"]=4;
    Mp["Mike"]=4;
    Mp["Ariel"]=4;
    Mp["Ann"]=4;
    Mp["Steve"]=4;
    Mp["Carolyn"]=5;
    Mp["Robert"]=4;
    Mp["Jesse"]=2;
    Mp["Starry"]=4;
    Mp["Javier"]=2;
    Mp["Sunny"]=4;
    Mp["Isaac"]=3;
    Mp["William"]=4;
    Mp["Cyan"]=3;
    Mp["Alex"]=1;
    Mp["Victoria"]=3;
    Mp["Stanley"]=0;
    Mp["Viola"]=0;
    Mp["Tim"]=3;
    Mp["Fred"]=2;
    Mp["-------"]=0;

    pr pStanley=Int2Coord(idStanley);
    if(idStanley<16) {//1st group
        pr curBest;
        int curVal=0;
        for(int i=0;i<8;i++) {
            int ux=pStanley.first+dx[i], uy=pStanley.second+dy[i];
            if(Coord2Int(mp(ux,uy))>=16) continue;
            if(getVal(ux,uy)>curVal) curBest=mp(ux,uy), curVal=getVal(ux,uy);
        }
        int curBestInt=Coord2Int(curBest);
        if(rng()%3) swap(name1[idStanley],name1[curBestInt]);
    }
    else {//2nd group
        pr curBest;
        int curVal=0;
        for(int i=0;i<8;i++) {
            int ux=pStanley.first+dx[i], uy=pStanley.second+dy[i];
            if(Coord2Int(mp(ux,uy))<16) continue;
            if(getVal(ux,uy)>curVal) curBest=mp(ux,uy), curVal=getVal(ux,uy);
        }
        int curBestInt=Coord2Int(curBest);
        if(!(rng()%3)) swap(name2[idStanley],name2[curBestInt]);
    }

    //写入 data
    freopen("data.txt","w",stdout);
    for(int i=0;i<16;i++) 
        cout<<name1[i]<<endl;
    for(int i=0;i<16;i++)
        cout<<name2[i]<<endl;
    fclose(stdout);
    //写入 seating
    freopen("seating.txt","w",stdout);
    cout<<"|||DOOR|||                        |||FRONT DESK|||\n\n\n\n";
    cout<<"---------    -------- --------    -------- ---------\n";
    printf("|%8s|  |%8s|%8s|  |%8s|%8s|\n",name1[0].c_str(),name1[1].c_str(),name1[2].c_str(),name1[3].c_str(),name1[4].c_str());
    cout<<"---------    -------- --------    -------- ---------\n";
    printf("|%8s|  |%8s|%8s|  |%8s|%8s|\n",name1[5].c_str(),name1[6].c_str(),name1[7].c_str(),name1[8].c_str(),name1[9].c_str());    
    cout<<"---------    -------- --------    -------- ---------\n";  
    printf("|%8s|  |%8s|%8s|  |%8s|%8s|\n",name1[10].c_str(),name1[11].c_str(),name1[12].c_str(),name1[13].c_str(),name1[14].c_str());
    cout<<"---------    -------- --------    -------- ---------\n";
    printf("|%8s|  |%8s|%8s|  |%8s|%8s|\n",name1[15].c_str(),name2[0].c_str(),name2[1].c_str(),name2[2].c_str(),name2[3].c_str());
    cout<<"---------    -------- --------    -------- ---------\n";
    printf("|%8s|  |%8s|%8s|  |%8s|%8s|\n",name2[4].c_str(),name2[5].c_str(),name2[6].c_str(),name2[7].c_str(),name2[8].c_str());
    cout<<"---------    -------- --------    -------- ---------\n";
    printf("|%8s|  |%8s|%8s|  |%8s|%8s|\n",name2[9].c_str(),name2[10].c_str(),name2[11].c_str(),name2[12].c_str(),name2[13].c_str());
    cout<<"---------    -------- --------    -------- ---------\n";
    printf("|        |  |        |%8s|  |        |        |\n",name2[14].c_str());
    cout<<"---------    -------- --------    -------- ---------\n";
    cout<<"\n\n\n\n                      |||BACK|||";
    fclose(stdout);
    return 0;
}
