package com.fx.note.airthmtic.leetcode


/**
 * @description
 * @author Created by 冯鑫 on 2022/2/10 13:14.
 */
public class LeetUtils {
    //    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。
//    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
//    示例:
//    给定 nums = [2, 7, 11, 15], target = 9
//    因为 nums[0] + nums[1] = 2 + 7 = 9
//    所以返回 [0, 1]
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map: HashMap<Int, Int> = HashMap()
        for (i in nums.indices.reversed()) { //反转遍历
            val firstNum = target - nums[i] //target-遍历的值
            if (map.containsKey(firstNum)) { //首次肯定不包含，上次循环保存在map里面的key 比较，如果一样，说明找到了
                return intArrayOf(i, map[firstNum]!!) //返回当前下标和上次循环的下标。
            } else {
                map[nums[i]] = i //key 等于 nums[i] 目的与上一步比较 。value 等于下标，下标目的是返回
            }
        }
        throw IllegalArgumentException()
    }

    //    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//    示例：
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode {
        val dummyHead = ListNode(0)
        var list1: ListNode? = l1
        var list2: ListNode? = l2
        var cur: ListNode = dummyHead
        // 用于表示进位
        var carry = 0
        var sum = 0
        while (list1 != null || list2 != null) {
            sum = carry
            if (list1 != null) {
                sum += list1.`val`
                list1 = list1.next
            }
            if (list2 != null) {
                sum += list2.`val`
                list2 = list2.next
            }
            carry = sum / 10
            cur.next = ListNode(sum % 10)
            cur = cur.next
        }
        if (carry == 1) {
            cur.next = ListNode(1)
        }
        return dummyHead.next
    }

    //无重复字符的最长子串
    fun lengthOfLongestSubstring(s: String): Int {
        val freq = IntArray(256)
        // 设定s的[left,right]子串无重复字符
        var l = 0
        var r = -1
        var max = 0
        while (l < s.length) {
            if (r == s.length - 1) {
                break
            }
            if (freq[s[r + 1].toInt()] == 0) { //如果存起来的没有就存能起来
                freq[s[r + 1].toInt()]++ //存起来用于比较
                r++ //没有的化就增加 计算max
                max = Math.max(max, r - l + 1)
            } else { //如果存起来的有 说明有重复了 ，往前走一步 就-- 并且l++
                freq[s[l].toInt()]-- //因为往前走了，所以当前这个不做比较了，所以=0 去除了
                l++
            }
        }
        return max
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val length1 = nums1.size
        val length2 = nums2.size
        val totalLength = length1 + length2
        return if (totalLength % 2 == 1) {
            val midIndex = totalLength / 2
            getKthElement(nums1, nums2, midIndex + 1).toDouble()
        } else {
            val midIndex1 = totalLength / 2 - 1
            val midIndex2 = totalLength / 2
            (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(
                nums1,
                nums2,
                midIndex2 + 1
            )) / 2.0
        }
    }

    fun getKthElement(nums1: IntArray, nums2: IntArray, k: Int): Int {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        var k = k
        val length1 = nums1.size
        val length2 = nums2.size
        var index1 = 0
        var index2 = 0
        val kthElement = 0
        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1]
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1]
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2])
            }

            // 正常情况
            val half = k / 2
            val newIndex1 = Math.min(index1 + half, length1) - 1
            val newIndex2 = Math.min(index2 + half, length2) - 1
            val pivot1 = nums1[newIndex1]
            val pivot2 = nums2[newIndex2]
            if (pivot1 <= pivot2) {
                k -= newIndex1 - index1 + 1
                index1 = newIndex1 + 1
            } else {
                k -= newIndex2 - index2 + 1
                index2 = newIndex2 + 1
            }
        }
    }

    //最长回文子串
    fun longestPalindrome(s: String): String {
        val len = s.length
        if (len < 2) {
            return s
        }
        var maxLen = 1
        var begin = 0
        // dp[i][j] 表示 s[i..j] 是否是回文串
        val dp = Array(len) {
            BooleanArray(
                len
            )
        }
        // 初始化：所有长度为 1 的子串都是回文串
        for (i in 0 until len) {
            dp[i][i] = true
        }
        val charArray = s.toCharArray()
        // 递推开始
        // 先枚举子串长度
        for (L in 2..len) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (i in 0 until len) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                val j = L + i - 1
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true
                    } else {
                        dp[i][j] = dp[i + 1][j - 1]
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1
                    begin = i
                }
            }
        }
        return s.substring(begin, begin + maxLen)
    }

    var symbolValues: HashMap<Char?, Int?> = object : HashMap<Char?, Int?>() {
        init {
            put('I', 1)
            put('V', 5)
            put('X', 10)
            put('L', 50)
            put('C', 100)
            put('D', 500)
            put('M', 1000)
        }
    }

    //罗马数字转整数
    fun romanToInt(s: String): Int {
        var ans = 0
        val n = s.length
        for (i in 0 until n) {
            val value = symbolValues[s[i]]!!
            if (i < n - 1 && value < symbolValues[s[i + 1]]!!) {
                ans -= value
            } else {
                ans += value
            }
        }
        return ans
    }

    fun reverse(x: Int): Int {
        var x = x
        var rev = 0
        while (x != 0) {
            if (rev < Int.MIN_VALUE / 10 || rev > Int.MAX_VALUE / 10) {
                return 0
            }
            // 弹出 x 的末尾数字 digit
            val digit = x % 10
            x /= 10
            // 将数字 digit 推入 rev 末尾
            rev = rev * 10 + digit
        }
        return rev
    }
   // 回文数
    fun isPalindrome(x: Int): Boolean {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        var x = x
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false
        }
        var revertedNumber = 0
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10
            x /= 10
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10
    }


    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2
        val prehead = ListNode(-1)
        var prev = prehead
        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                prev.next = l1
                l1 = l1.next
            } else {
                prev.next = l2
                l2 = l2.next
            }
            prev = prev.next
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 ?: l2
        return prehead.next
    }


    fun letterCombinations(digits: String): List<String?>? {
        val combinations: ArrayList<String> = ArrayList()
        if (digits.length == 0) {
            return combinations
        }
        val phoneMap: HashMap<Char?, String?> = object : HashMap<Char?, String?>() {
            init {
                put('2', "abc")
                put('3', "def")
                put('4', "ghi")
                put('5', "jkl")
                put('6', "mno")
                put('7', "pqrs")
                put('8', "tuv")
                put('9', "wxyz")
            }
        }
        backtrack(combinations, phoneMap, digits, 0, StringBuffer())
        return combinations
    }

    fun backtrack(
        combinations: ArrayList<String>,
        phoneMap: Map<Char?, String?>,
        digits: String,
        index: Int,
        combination: StringBuffer
    ) {
        if (index == digits.length) {
            combinations.add(combination.toString())
        } else {
            val digit = digits[index]
            val letters = phoneMap[digit]
            val lettersCount = letters!!.length
            for (i in 0 until lettersCount) {
                combination.append(letters[i])
                backtrack(combinations, phoneMap, digits, index + 1, combination)
                combination.deleteCharAt(index)
            }
        }
    }

    //接雨水
    fun trap(height: IntArray): Int {
        var ans = 0
        val size = height.size
        for (i in 1 until size - 1) {
            var max_left = 0
            var max_right = 0
            for (j in i downTo 0) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j])
            }
            for (j in i until size) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j])
            }
            ans += Math.min(max_left, max_right) - height[i]
        }
        return ans
    }
}