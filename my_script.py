import requests

def get_place_id(api_key, place_name, address):
    url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json"
    params = {
        'input': f'{place_name}, {address}',
        'inputtype': 'textquery',
        'fields': 'place_id',
        'key': api_key
    }
    
    response = requests.get(url, params=params)
    print(response.url)  # 요청 URL 출력
    if response.status_code == 200:
        result = response.json()
        print(result)  # 응답 내용 출력
        if 'candidates' in result and len(result['candidates']) > 0:
            return result['candidates'][0]['place_id']
        else:
            return None
    else:
        print(f"Error: {response.status_code}")
        return None

def get_place_reviews(api_key, place_id):
    url = f"https://maps.googleapis.com/maps/api/place/details/json?placeid={place_id}&key={api_key}"
    response = requests.get(url)
    
    if response.status_code == 200:
        result = response.json()
        if 'result' in result:
            place_details = result['result']
            reviews = place_details.get('reviews', [])
            return reviews
        else:
            return []
    else:
        print(f"Error: {response.status_code}")
        return []

def calculate_average_rating(reviews):
    if not reviews:
        return None
    ratings = [review.get('rating') for review in reviews if review.get('rating') is not None]
    if not ratings:
        return None
    average_rating = sum(ratings) / len(ratings)
    return average_rating

def main():
    api_key = 'AIzaSyC3A3ihIWjK9vkIQjMhNljou_3HGukiUG4'  # Google Maps Platform에서 생성한 API 키를 여기에 입력하세요.
    place_name = '해림 횟집'  # 여기서 원하는 장소 이름을 입력하세요.
    address = '강원특별자치도 동해시 동해대로 6270-31'  # 장소의 정확한 주소를 입력하세요.
    
    place_id = get_place_id(api_key, place_name, address)
    if place_id:
        print(f'Place ID for {place_name}: {place_id}')
        reviews = get_place_reviews(api_key, place_id)
        if reviews:
            average_rating = calculate_average_rating(reviews)
            if average_rating is not None:
                print(f'Average Rating: {average_rating:.2f}')
            else:
                print('No valid ratings found.')
            for review in reviews:
                print("Author:", review.get('author_name'))
                print("Rating:", review.get('rating'))
                print("Text:", review.get('text'))
                print("Time:", review.get('relative_time_description'))
                print("-" * 40)
        else:
            print('No reviews found.')
    else:
        print('Place not found')

if __name__ == "__main__":
    main()
