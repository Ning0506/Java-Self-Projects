///////////////////////////////////////////
// Author: Ning Yang
// Purpose: realize the FIFO and RR CPU
//          scheduling algorithm
///////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXN 1025
#define READY 0
#define RUNNING 1
#define BLOCKED 2
#define Terminate 3
#define FINISH 4

typedef struct process {
    int stage;
    int idx;
    int cnt;
    int time[5];
} Process;

int n, type, q;
Process P[MAXN];
//Ready Queue
int queue[MAXN], hh, tt;

int st[MAXN];
int inQueue[MAXN];

void push(int u) {
    queue[tt] = u;
    tt += 1;
    if (tt == MAXN)
        tt = 0;
}

void pop() {
    hh += 1;
    if (hh == MAXN)
        hh = 0;
}

void FIFO() {
    int curTime = 0;
    int finish = 0;
    printf("Cycle\t");
    for (int i = 1; i <= n; i ++ )
        printf("P%d State\t\t", i);
    printf("Comment\t\n");

    while (finish != n ) {
        printf("%d\t", curTime + 1);
        int curP = queue[hh];
        P[curP].stage = RUNNING;
        for (int i = 1; i <= n; i ++ ) {
            // running but not Terminate.
            if (P[i].stage == RUNNING && P[i].idx != 5) {
                P[i].cnt += 1;
                printf("Run (%d of %d)\t\t", P[i].cnt, P[i].time[P[i].idx]);
                if (P[i].cnt == P[i].time[P[i].idx]) {
                    P[i].cnt = 0, P[i].idx += 1;
                    P[i].stage = BLOCKED;
                    // BLOCKED, pop from queue
                    pop();
                }
            }
            else if (P[i].stage == READY)
                printf("Ready\t\t\t");
            else if (P[i].stage == BLOCKED) {
                P[i].cnt += 1;
                printf("Blocked  (%d of %d)\t", P[i].cnt, P[i].time[P[i].idx]);
                if (P[i].cnt == P[i].time[P[i].idx]) {
                    P[i].cnt = 0, P[i].idx += 1;
                    // READY, push to queue
                    push(i);
                    P[i].stage = READY;
                }
            }
            else if (P[i].stage == RUNNING && P[i].idx == 5) {
                printf("Terminate\t\t"), finish += 1;
                P[i].stage = FINISH;
                pop();
            } else if (P[i].stage == FINISH)
                printf("\t\t\t");
        }
        curTime += 1;
        if (curP > 0)
            printf("P%d running\t", curP);
        else
            printf("CPU idle\t");
        printf("\n");
    }
}

void RR() {
    int curTime = 0;
    int finish = 0;
    printf("Cycle\t");
    for (int i = 1; i <= n; i ++ )
        printf("P%d State\t\t", i);
    printf("Comment\t\n");

    int beforeP = -1;
    while (finish != n ) {
        int curP = -1;
        if (hh != tt) {
            curP = queue[hh];
            P[curP].stage = RUNNING;
            // Run the ready process, pop from queue
            pop();
            inQueue[curP] = 0;
        }

        int newCycle = 0;
        for (int t = 1; t <= q; t ++ ) {
            curTime += 1;
            printf("%d\t", curTime);
            for (int i = 1; i <= n; i ++ ) {
                if (P[i].stage == RUNNING) {
                    P[i].cnt += 1;
                    if (P[i].idx == 1 || P[i].idx == 3)
                        printf("Run (%d of %d)\t\t", P[i].cnt, P[i].time[P[i].idx]);
                    else if (P[i].idx == 2 || P[i].idx == 4)
                        printf("Blocked  (%d of %d)\t", P[i].cnt, P[i].time[P[i].idx]);
                    else if (st[i] == 0) {
                        printf("Terminate\t\t");
                        finish += 1;
                        st[i] = 1;
                        P[i].stage = FINISH;
                    } else if (st[i] == 1) {
                        printf("\t\t\t");
                    }
                    if (P[i].idx != 5 && P[i].cnt == P[i].time[P[i].idx]) {
                        P[i].cnt = 0, P[i].idx += 1;
                    }

                    if (P[i].idx == 2 || P[i].idx == 4 || P[i].idx == 5) {
                        newCycle = 1;
                    }
                } else if (P[i].stage == READY) {
                    printf("Ready\t\t\t");
                } else if (P[i].stage == BLOCKED) {
                    P[i].cnt += 1;
                    printf("Blocked  (%d of %d)\t", P[i].cnt, P[i].time[P[i].idx]);
                    if (P[i].cnt == P[i].time[P[i].idx]) {
                        P[i].cnt = 0, P[i].idx += 1;
                        P[i].stage = READY;
                    }
                } else if (P[i].stage == FINISH)
                        printf("\t\t\t");
            }

            if (curP != -1 && beforeP != -1 && t == 1 && P[beforeP].stage == READY && P[beforeP].time[P[beforeP].idx - 1] != 1) {
                printf("P%d preempted\t", beforeP);
            }
            if (curP == -1) {
                printf("CPU idle\t");
                beforeP = -1;
            } else
                beforeP = curP;
            if (curTime == 1) {
                printf("Both created; P1 wins tiebreak\t");
            }
            printf("\n");

            if (newCycle || curP == -1)
                break;
        }
        if (curP != -1 && P[curP].stage != FINISH) {

            if (P[curP].idx == 1 || P[curP].idx == 3) {
                P[curP].stage = READY;
            }
            else if (P[curP].idx == 2 || P[curP].idx == 4)
                P[curP].stage = BLOCKED;
        }
        for (int i = 1; i <= n; i ++ ) {
            if (P[i].stage == READY && inQueue[i] == 0)
                push(i), inQueue[i] = 1;
        }
    }
}

int main() {
    scanf("%d %d", &n, &type);
    // 1-FIFO, 2-RR
    // init for cycle queue
    hh = 0, tt = 0;
    if (type == 2)
        scanf("%d", &q);

    for (int i = 1; i <= n; i ++ ) {
        for (int j = 1; j <= 4; j ++ )
            scanf("%d", &P[i].time[j]);
        P[i].stage = READY;
        P[i].idx = 1, P[i].cnt = 0;
        // push queue
        push(i);
        inQueue[i] = 1;
    }
    if (type == 1)
        FIFO();
    else if (type == 2)
        RR();

    return 0;
}
