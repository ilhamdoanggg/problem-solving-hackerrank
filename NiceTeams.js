skillLevel.sort((a, b) => a - b);
    let n = skillLevel.length;
    let i = 0;
    let x = [];
    for (let j = 0; j < Math.floor(n / 2); j++) {
        while (i < n && skillLevel[i] - skillLevel[j] < minDiff) {
            i++;
        }
        if (i >= n) {
            break;
        }
        x.push(i);
    }

    x = x.slice(0, Math.min(Math.floor(n / 2), x.length));
    let result = 0;
    let k = n - 1;

    for (let idx = x.length - 1; idx >= 0; idx--) {
        let y = x[idx];
        if (y <= k) {
            result++;
            k--;
        }
    }

    return result;
