//updated on 21/05/18

#include<iostream>
#include<string.h>
#include<cstdio>
#include<chrono>
#include<random>
#include<vector>
using namespace std;
long long a;
mt19937_64 rng(chrono::steady_clock::now().time_since_epoch().count());
//printf("%llu\n", rng());
int numOfStudents;
//vector<string>...

bool book[31];
string name1[16];
string name2[16];
//string name[]
string tmp[16];
int num;
int main() {
    //读入
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

    //写入 data
    freopen("data.txt","w",stdout);

    for(int i=0;i<16;i++) 
        cout<<name1[i]<<endl;
    for(int i=0;i<16;i++)
        cout<<name2[i]<<endl;
    fclose(stdout);
    //写入 seating
    freopen("seating.txt","w",stdout);
    cout<<"|||DOOR|||                 |||FRONT DESK|||             \n\n\n\n";
    cout<<"--------- -------- -------- -------- ---------\n";
    printf("|%8s|%8s|%8s|%8s|%8s|\n",name1[0].c_str(),name1[1].c_str(),name1[2].c_str(),name1[3].c_str(),name1[4].c_str());
    cout<<"--------- -------- -------- -------- ---------\n";
    printf("|%8s|%8s|%8s|%8s|%8s|\n",name1[5].c_str(),name1[6].c_str(),name1[7].c_str(),name1[8].c_str(),name1[9].c_str());    
    cout<<"--------- -------- -------- -------- ---------\n";  
    printf("|%8s|%8s|%8s|%8s|%8s|\n",name1[10].c_str(),name1[11].c_str(),name1[12].c_str(),name1[13].c_str(),name1[14].c_str());
    cout<<"--------- -------- -------- -------- ---------\n";
    printf("|%8s|%8s|%8s|%8s|%8s|\n",name1[15].c_str(),name2[0].c_str(),name2[1].c_str(),name2[2].c_str(),name2[3].c_str());
    cout<<"--------- -------- -------- -------- ---------\n";
    printf("|%8s|%8s|%8s|%8s|%8s|\n",name2[4].c_str(),name2[5].c_str(),name2[6].c_str(),name2[7].c_str(),name2[8].c_str());
    cout<<"--------- -------- -------- -------- ---------\n";
    printf("|%8s|%8s|%8s|%8s|%8s|\n",name2[9].c_str(),name2[10].c_str(),name2[11].c_str(),name2[12].c_str(),name2[13].c_str());
    cout<<"--------- -------- -------- -------- ---------\n";
    printf("|        |        |%8s|        |        |\n",name2[14].c_str());
    cout<<"--------- -------- -------- -------- ---------\n";
    cout<<"\n\n\n\n                      |||BACK|||          \n\n";
    fclose(stdout);
    return 0;
}
