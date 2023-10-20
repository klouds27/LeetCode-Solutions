function maxScore(nums1: number[], nums2: number[], k: number): number {
    const n = nums1.length;
    const pairs: [number, number][] = [];

    for (let i = 0; i < n; i++) {
        pairs.push([nums1[i], nums2[i]]);
    }

    pairs.sort((a, b) => b[1] - a[1]);

    const maxHeap: number[] = [];
    let currentSum = 0;

    for (let i = 0; i < k; i++) {
        currentSum += pairs[i][0];
        binaryInsert(maxHeap, pairs[i][0]);
    }

    let maxResult = currentSum * pairs[k - 1][1];

    for (let i = k; i < n; i++) {
        currentSum += pairs[i][0] - maxHeap.shift();
        binaryInsert(maxHeap, pairs[i][0]);
        maxResult = Math.max(maxResult, currentSum * pairs[i][1]);
    }

    return maxResult;
}

function binaryInsert(arr: number[], item: number): void {
    let left = 0;
    let right = arr.length;

    while (left < right) {
        const mid = (left + right) >> 1;

        if (arr[mid] > item) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    arr.splice(left, 0, item);
}